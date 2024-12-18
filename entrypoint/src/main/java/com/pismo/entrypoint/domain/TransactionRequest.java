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
public class TransactionRequest {
    @JsonProperty("account_id")
    @NotBlank(message = "Account ID must not be null or empty")
    private String accountId;
    @JsonProperty("operation_type_id")
    @NotBlank(message = "Operation Type ID must not be null or empty")
    private int operationTypeId;
    @NotBlank(message = "Amount must not be null or empty")
    private BigDecimal amount;

    public Transaction toDomain() {
        return Transaction.builder()
                .account(Account.builder().accountId(accountId).build())
                .operationType(OperationType.builder()
                        .operationTypeId(operationTypeId)
                        .build())
                .amount(amount).build();
    }
}
