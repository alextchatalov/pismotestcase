package com.prismo.core.account;

import com.prismo.core.account.domain.Account;

public interface CreateAccountUseCase {
    void execute(final Account account);
}
