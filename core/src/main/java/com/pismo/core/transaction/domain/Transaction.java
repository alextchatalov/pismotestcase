package com.pismo.core.transaction.domain;

import com.pismo.core.account.domain.Account;
import com.pismo.core.operationType.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
public class Transaction {
    private Account account;
    private OperationType operationType;
    private BigDecimal amount;
}
