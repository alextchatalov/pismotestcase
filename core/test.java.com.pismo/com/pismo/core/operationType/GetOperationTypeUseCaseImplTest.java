package com.pismo.core.operationType;

import com.pismo.core.exception.NotFoundException;
import com.pismo.gateway.account.GetOperationTypeGateway;
import com.pismo.gateway.account.domain.OperationTypeEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetOperationTypeUseCaseImplTest {

    @Mock
    private GetOperationTypeGateway getOperationTypeGateway;

    @InjectMocks
    private GetOperationTypeUseCaseImpl getOperationTypeUseCaseImpl;

    public GetOperationTypeUseCaseImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnOperationTypeEntityWhenFound() {
        int operationTypeId = 1;
        OperationTypeEntity expectedEntity = new OperationTypeEntity(operationTypeId);

        when(getOperationTypeGateway.execute(operationTypeId)).thenReturn(Optional.of(expectedEntity));

        OperationTypeEntity result = getOperationTypeUseCaseImpl.execute(operationTypeId);

        assertNotNull(result);
        assertEquals(expectedEntity, result);
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
}