package com.prismo.core.transaction.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
public class Transaction {
    private int accountId;
    private int operationTypeId;
    private BigDecimal amount;
}
