package com.pismo.entrypoint.account;

import com.pismo.entrypoint.account.domain.TransactionEntity;

public interface CreateTransactionGateway {
    TransactionEntity execute(TransactionEntity transactionEntity);
}
