package com.pismo.core.operationType;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OperationType {
    private int operationTypeId;
    private String description;
}
