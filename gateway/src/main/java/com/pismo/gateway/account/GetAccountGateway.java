package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.AccountEntity;

import java.util.Optional;

public interface GetAccountGateway {
    Optional<AccountEntity> execute(String accountID);
}
