package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.AccountEntity;

public interface GetOperationTypeGateway {
    AccountEntity execute(String accountID);
}
