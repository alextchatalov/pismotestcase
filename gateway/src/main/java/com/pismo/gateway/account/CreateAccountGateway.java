package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.AccountEntity;

public interface CreateAccountGateway {
    AccountEntity execute(AccountEntity accountEntity);
}
