package com.pismo.entrypoint.domain;

import com.prismo.core.transaction.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class TransactionRequest {
    private int accountId;
    private int operationTypeId;
    private BigDecimal amount;

    public Transaction toDomain() {
        return new Transaction(accountId, operationTypeId, amount);
    }
}
