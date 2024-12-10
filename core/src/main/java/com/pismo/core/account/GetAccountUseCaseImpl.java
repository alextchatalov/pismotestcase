package com.pismo.core.account;

import com.pismo.core.account.domain.Account;
import com.pismo.core.exception.NotFoundException;
import com.pismo.gateway.account.GetAccountGateway;
import com.pismo.gateway.account.domain.AccountEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetAccountUseCaseImpl implements GetAccountUseCase {

    private final GetAccountGateway gateway;

    public GetAccountUseCaseImpl(GetAccountGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Account execute(String accountId) {
        Optional<AccountEntity> entity = gateway.execute(accountId);
        if (entity.isEmpty()) {
            throw new NotFoundException("Account not found with ID: " + accountId);
        }
        return Account.builder()
                .accountId(entity.get().getAccountId())
                .documentNumber(entity.get().getDocumentNumber())
                .build();
    }
}
