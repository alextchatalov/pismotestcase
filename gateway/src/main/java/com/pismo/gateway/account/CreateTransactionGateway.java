package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.TransactionEntity;

public interface CreateTransationGateway {
    TransactionEntity execute(TransactionEntity transactionEntity);
}
