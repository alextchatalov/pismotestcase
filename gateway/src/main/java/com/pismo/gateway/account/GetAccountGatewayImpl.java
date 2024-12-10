package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.AccountEntity;
import com.pismo.gateway.account.repository.AccountRepository;
import com.prismo.core.account.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class GetAccountGatewayImpl implements CreateAccountGateway {

    private final AccountRepository accountRepository;

    public GetAccountGatewayImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void execute(Account account) {
        AccountEntity accountEntity = new AccountEntity(account.getAccountId(), account.getDocumentNumber());
        accountRepository.save(accountEntity);
    }
}
