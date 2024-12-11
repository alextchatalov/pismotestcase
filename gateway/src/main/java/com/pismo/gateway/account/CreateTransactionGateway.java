package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.TransactionEntity;

public interface CreateTransactionGateway {
    TransactionEntity execute(TransactionEntity transactionEntity);
}
