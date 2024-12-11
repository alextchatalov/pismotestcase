package com.pismo.entrypoint;

import com.pismo.core.account.CreateAccountUseCase;
import com.pismo.core.account.GetAccountUseCase;
import com.pismo.core.account.domain.Account;
import com.pismo.entrypoint.domain.AccountRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private CreateAccountUseCase createAccountUseCase;

    @Mock
    private GetAccountUseCase getAccountUseCase;

    @InjectMocks
    private AccountController accountController;

    @Test
    @DisplayName("Should create an account and return HTTP 201")
    void createAccount_Success() {
        // Arrange
        AccountRequest accountRequest = AccountRequest.builder()
                .accountId("1")
                .documentNumber("12345678900")
                .build();

        Account mockAccount = Account.builder()
                .accountId("1")
                .documentNumber("12345678900")
                .build();

        when(createAccountUseCase.execute(any())).thenReturn(mockAccount);

        // Act
        ResponseEntity<AccountRequest> response = accountController.createAccount(accountRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(accountRequest.getAccountId(), response.getBody().getAccountId());
        assertEquals(accountRequest.getDocumentNumber(), response.getBody().getDocumentNumber());
        Mockito.verify(createAccountUseCase).execute(any());
    }

    @Test
    @DisplayName("Should retrieve account information by account ID")
    void getAccountInfo_Success() {
        // Arrange
        String accountId = "1";
        Account mockAccount = Account.builder()
                .accountId("1")
                .documentNumber("12345678900")
                .build();

        when(getAccountUseCase.execute(accountId)).thenReturn(mockAccount);

        // Act
        AccountRequest response = accountController.getAccountInfo(accountId);

        // Assert
        assertEquals(accountId, response.getAccountId());
        assertEquals("12345678900", response.getDocumentNumber());
        Mockito.verify(getAccountUseCase).execute(accountId);
    }
}