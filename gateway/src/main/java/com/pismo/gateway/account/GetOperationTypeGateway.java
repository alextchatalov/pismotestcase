package com.pismo.entrypoint.account;

import com.pismo.entrypoint.account.domain.OperationTypeEntity;

import java.util.Optional;

public interface GetOperationTypeGateway {
    Optional<OperationTypeEntity> execute(int operationTypeId);
}
