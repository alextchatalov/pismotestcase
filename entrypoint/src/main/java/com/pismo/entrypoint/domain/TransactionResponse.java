package com.pismo.entrypoint.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pismo.core.account.domain.Account;
import com.pismo.core.operationType.OperationType;
import com.pismo.core.transaction.domain.Transaction;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class TransactionResponse {
    private Account account;
    private OperationType operationType;
    private BigDecimal amount;

    public static TransactionResponse toResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .account(transaction.getAccount())
                .operationType(transaction.getOperationType())
                .amount(transaction.getAmount())
                .build();
    }
}
