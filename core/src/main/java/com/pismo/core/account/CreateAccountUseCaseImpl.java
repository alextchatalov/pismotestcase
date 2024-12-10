package com.prismo.core.account;

import com.pismo.gateway.account.CreateAccountGateway;
import com.pismo.gateway.account.domain.AccountEntity;
import com.prismo.core.account.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final CreateAccountGateway gateway;

    public CreateAccountUseCaseImpl(CreateAccountGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void execute(Account account) {
        AccountEntity accountEntity = new AccountEntity(account.getAccountId(), account.getDocumentNumber());
        gateway.execute(accountEntity);
    }
}
