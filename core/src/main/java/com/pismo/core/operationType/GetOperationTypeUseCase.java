package com.pismo.core.operationType;

import com.pismo.gateway.account.domain.OperationTypeEntity;

public interface GetOperationTypeUseCase {
    OperationTypeEntity execute(final int operationTypeId);
}
