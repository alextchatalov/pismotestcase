package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.AccountEntity;
import com.pismo.gateway.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetAccountGatewayImpl implements GetAccountGateway {

    private final AccountRepository accountRepository;

    public GetAccountGatewayImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<AccountEntity> execute(String accountId) {
        return accountRepository.findById(accountId);
    }
}
