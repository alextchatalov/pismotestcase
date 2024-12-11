package com.pismo.entrypoint.account;

import com.pismo.entrypoint.account.domain.AccountEntity;

import java.util.Optional;

public interface GetAccountGateway {
    Optional<AccountEntity> execute(String accountID);
}
