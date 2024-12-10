package com.prismo.core.transaction;

import com.prismo.core.account.domain.Account;
import com.prismo.core.transaction.domain.Transaction;

public interface CreateTransactionUseCase {
    void execute(final Transaction transaction);
}
