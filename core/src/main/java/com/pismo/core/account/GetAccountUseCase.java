package com.pismo.core.account;

import com.pismo.core.account.domain.Account;

public interface GetAccountUseCase {
    Account execute(final String accountId);
}
