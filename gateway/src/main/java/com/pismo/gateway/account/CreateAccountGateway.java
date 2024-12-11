package com.pismo.entrypoint.account;

import com.pismo.entrypoint.account.domain.AccountEntity;

public interface CreateAccountGateway {
    AccountEntity execute(AccountEntity accountEntity);
}
