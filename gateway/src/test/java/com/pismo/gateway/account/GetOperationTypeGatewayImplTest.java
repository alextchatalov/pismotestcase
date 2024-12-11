package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.OperationTypeEntity;
import com.pismo.gateway.account.repository.OperationTypeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class GetOperationTypeGatewayImplTest {

    @Mock
    private OperationTypeRepository operationTypeRepository;

    @InjectMocks
    private GetOperationTypeGatewayImpl getOperationTypeGatewayImpl;

    public GetOperationTypeGatewayImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ReturnsOperationTypeEntity_WhenIdExists() {
        int validOperationTypeId = 1;
        OperationTypeEntity expectedEntity = new OperationTypeEntity(validOperationTypeId, "test");

        when(operationTypeRepository.findById(validOperationTypeId)).thenReturn(Optional.of(expectedEntity));

        Optional<OperationTypeEntity> result = getOperationTypeGatewayImpl.execute(validOperationTypeId);

        assertTrue(result.isPresent());
        assertEquals(expectedEntity, result.get());
    }

    @Test
    void testExecute_ReturnsEmptyOptional_WhenIdDoesNotExist() {
        int invalidOperationTypeId = 2;

        when(operationTypeRepository.findById(invalidOperationTypeId)).thenReturn(Optional.empty());

        Optional<OperationTypeEntity> result = getOperationTypeGatewayImpl.execute(invalidOperationTypeId);

        assertTrue(result.isEmpty());
    }

    @Test
    void testExecute_ReturnsEmptyOptional_WhenNegativeIdProvided() {
        int negativeOperationTypeId = -1;

        when(operationTypeRepository.findById(negativeOperationTypeId)).thenReturn(Optional.empty());

        Optional<OperationTypeEntity> result = getOperationTypeGatewayImpl.execute(negativeOperationTypeId);

        assertTrue(result.isEmpty());
    }

    @Test
    void testExecute_CallsRepositoryFindById_WithCorrectValue() {
        int validOperationTypeId = 1;

        getOperationTypeGatewayImpl.execute(validOperationTypeId);

        verify(operationTypeRepository).findById(validOperationTypeId);
    }
}