package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.OperationTypeEntity;

import java.util.Optional;

public interface GetOperationTypeGateway {
    Optional<OperationTypeEntity> execute(int operationTypeId);
}
