package com.pismo.core.operationType;

import com.pismo.core.account.domain.Account;
import com.pismo.core.exception.NotFoundException;
import com.pismo.gateway.account.GetAccountGateway;
import com.pismo.gateway.account.GetOperationTypeGateway;
import com.pismo.gateway.account.domain.AccountEntity;
import com.pismo.gateway.account.domain.OperationTypeEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetOperationTypeUseCaseImpl implements GetOperationTypeUseCase {

    private final GetOperationTypeGateway getOperationTypeGateway;

    public GetOperationTypeUseCaseImpl(GetOperationTypeGateway getOperationTypeGateway) {
        this.getOperationTypeGateway = getOperationTypeGateway;
    }

    @Override
    public OperationTypeEntity execute(int operationTypeId) {
        Optional<OperationTypeEntity> operationTypeEntity = getOperationTypeGateway.execute(operationTypeId);
        if (operationTypeEntity.isEmpty()) {
            throw new NotFoundException("Operation Type not found with ID: " + operationTypeId);
        }
        return operationTypeEntity.get();
    }
}
