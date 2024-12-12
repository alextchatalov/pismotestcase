package com.pismo.core.operationType;

import com.pismo.core.exception.NotFoundException;
import com.pismo.gateway.account.GetOperationTypeGateway;
import com.pismo.gateway.account.domain.OperationTypeEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.eq;

class GetOperationTypeUseCaseImplTest {

    @Mock
    private GetOperationTypeGateway getOperationTypeGateway;
    @Mock
    private RedisTemplate<Integer, Object> redisTemplate;

    @InjectMocks
    private GetOperationTypeUseCaseImpl getOperationTypeUseCaseImpl;
    @Mock
    private ValueOperations<Integer, Object> valueOperations;

    public GetOperationTypeUseCaseImplTest() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void shouldReturnOperationTypeEntityWhenFound() {
        int operationTypeId = 1;
        OperationTypeEntity expectedEntity = new OperationTypeEntity(operationTypeId, "Test");

        when(getOperationTypeGateway.execute(operationTypeId)).thenReturn(Optional.of(expectedEntity));

        OperationType result = getOperationTypeUseCaseImpl.execute(operationTypeId);

        assertNotNull(result);
        assertEquals(expectedEntity.getId(), result.getOperationTypeId());
        assertEquals(expectedEntity.getDescription(), result.getDescription());
        verify(getOperationTypeGateway, times(1)).execute(operationTypeId);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenOperationTypeNotFound() {
        int operationTypeId = 2;

        when(getOperationTypeGateway.execute(operationTypeId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> getOperationTypeUseCaseImpl.execute(operationTypeId));

        assertEquals("Operation Type not found with ID: " + operationTypeId, exception.getMessage());
        verify(getOperationTypeGateway, times(1)).execute(operationTypeId);
    }
    @Test
    void shouldReturnOperationTypeEntityFromRedisWhenPresent() {
        int operationTypeId = 1;
        OperationType operationTypeCached = new OperationType(operationTypeId, "Cached");

        when(getOperationTypeGateway.execute(operationTypeId)).thenReturn(Optional.empty());
        when(redisTemplate.opsForValue().get(eq(operationTypeId))).thenReturn(operationTypeCached);

        OperationType result = getOperationTypeUseCaseImpl.execute(operationTypeId);

        assertNotNull(result);
        assertEquals(operationTypeCached, result);

        verify(redisTemplate.opsForValue(), times(1)).get(eq(operationTypeId));
        verify(getOperationTypeGateway, never()).execute(operationTypeId);
    }

    @Test
    void shouldCacheOperationTypeEntityWhenFetchedFromGateway() {
        int operationTypeId = 1;
        OperationTypeEntity fetchedEntity = new OperationTypeEntity(operationTypeId, "Fetched");

        when(redisTemplate.opsForValue().get(eq(operationTypeId))).thenReturn(null);
        when(getOperationTypeGateway.execute(operationTypeId)).thenReturn(Optional.of(fetchedEntity));

        OperationType result = getOperationTypeUseCaseImpl.execute(operationTypeId);

        assertNotNull(result);
        assertEquals(fetchedEntity.getId(), result.getOperationTypeId());
        assertEquals(fetchedEntity.getDescription(), result.getDescription());

        verify(redisTemplate.opsForValue(), times(1)).get(eq(operationTypeId));
        verify(getOperationTypeGateway, times(1)).execute(operationTypeId);
        verify(redisTemplate.opsForValue(), times(1)).set(eq(operationTypeId), eq(result));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenNoValueInCacheAndGateway() {
        int operationTypeId = 999;
        when(redisTemplate.opsForValue().get(eq(operationTypeId))).thenReturn(null);

        when(getOperationTypeGateway.execute(operationTypeId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> getOperationTypeUseCaseImpl.execute(operationTypeId));

        assertEquals("Operation Type not found with ID: " + operationTypeId, exception.getMessage());

        verify(redisTemplate.opsForValue(), times(1)).get(eq(operationTypeId));
        verify(getOperationTypeGateway, times(1)).execute(operationTypeId);
    }

}