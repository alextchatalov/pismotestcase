package com.pismo.entrypoint.account;

import com.pismo.entrypoint.account.domain.AccountEntity;
import com.pismo.entrypoint.account.repository.AccountRepository;
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
