package com.pismo.core.account;

import com.pismo.gateway.account.CreateAccountGateway;
import com.pismo.gateway.account.domain.AccountEntity;
import com.pismo.core.account.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final CreateAccountGateway gateway;

    public CreateAccountUseCaseImpl(CreateAccountGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Account execute(Account account) {
        AccountEntity accountEntity = new AccountEntity(account.getAccountId(), account.getDocumentNumber());
        AccountEntity savedAccount = gateway.execute(accountEntity);
        return Account.builder()
                .accountId(savedAccount.getAccountId())
                .documentNumber(account.getDocumentNumber())
                .build();
    }
}
