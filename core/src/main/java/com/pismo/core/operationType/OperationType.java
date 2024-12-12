package com.pismo.core.operationType;

import com.pismo.gateway.account.domain.OperationTypeEntity;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class OperationType implements Serializable {
    private int operationTypeId;
    private String description;

    public OperationTypeEntity toEntity() {
        return new OperationTypeEntity(operationTypeId, description);
    }
}
