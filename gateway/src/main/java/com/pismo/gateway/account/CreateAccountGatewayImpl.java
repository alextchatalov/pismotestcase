package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.AccountEntity;
import com.pismo.gateway.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountGatewayImpl implements CreateAccountGateway {

    private final AccountRepository accountRepository;

    public CreateAccountGatewayImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountEntity execute(AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
    }
}
