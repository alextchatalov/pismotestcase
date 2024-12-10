package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.AccountEntity;
import com.pismo.gateway.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class GetOperationTypeGatewayImpl implements GetAccountGateway {

    private final AccountRepository accountRepository;

    public GetOperationTypeGatewayImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountEntity execute(String accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }
}
