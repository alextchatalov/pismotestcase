package com.pismo.core.account;

import com.pismo.core.account.domain.Account;

public interface CreateAccountUseCase {
    Account execute(final Account account);
}
