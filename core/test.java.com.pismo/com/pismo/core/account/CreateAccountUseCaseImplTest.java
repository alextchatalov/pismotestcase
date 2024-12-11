package com.pismo.core.account;

import com.pismo.core.account.domain.Account;
import com.pismo.gateway.account.CreateAccountGateway;
import com.pismo.gateway.account.domain.AccountEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateAccountUseCaseImplTest {

    @Mock
    private CreateAccountGateway createAccountGateway;

    @InjectMocks
    private CreateAccountUseCaseImpl createAccountUseCaseImpl;

    public CreateAccountUseCaseImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAccountSuccessfully() {
        Account inputAccount = Account.builder()
                .accountId("1")
                .documentNumber("12345678900")
                .build();

        AccountEntity mockAccountEntity = new AccountEntity("1", "12345678900");
        AccountEntity savedAccountEntity = new AccountEntity("1", "12345678900");

        when(createAccountGateway.execute(any(AccountEntity.class))).thenReturn(savedAccountEntity);

        Account result = createAccountUseCaseImpl.execute(inputAccount);

        assertEquals(result.getAccountId(), savedAccountEntity.getAccountId());
        assertEquals(result.getDocumentNumber(), savedAccountEntity.getDocumentNumber());
    }
}