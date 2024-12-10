package com.prismo.core.account;

import com.prismo.core.account.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class GetAccountUseCaseImpl implements GetAccountUseCase {
    @Override
    public Account execute(String accountId) {
        return Account.builder().accountId("123").documentNumber("123").build();
    }
}
