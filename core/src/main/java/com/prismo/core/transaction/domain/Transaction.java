package com.prismo.core.transaction.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
public class Transaction {
    private int transactionId;
    private int operationTypeId;
    private BigDecimal amount;
}
