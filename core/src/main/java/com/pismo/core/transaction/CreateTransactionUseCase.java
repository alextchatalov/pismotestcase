package com.pismo.core.transaction;

import com.pismo.core.transaction.domain.Transaction;

public interface CreateTransactionUseCase {

    Transaction execute(final Transaction transaction);
}
