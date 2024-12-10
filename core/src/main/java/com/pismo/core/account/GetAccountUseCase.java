package com.prismo.core.account;

import com.prismo.core.account.domain.Account;

public interface GetAccountUseCase {
    Account execute(final String accountId);
}
