package com.pismo.core.account;

import com.pismo.core.account.domain.Account;
import com.pismo.core.exception.NotFoundException;
import com.pismo.gateway.account.GetAccountGateway;
import com.pismo.gateway.account.domain.AccountEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAccountUseCaseImplTest {

    @Mock
    private GetAccountGateway gateway;

    @InjectMocks
    private GetAccountUseCaseImpl getAccountUseCase;

    public GetAccountUseCaseImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ValidAccountId_ReturnsAccount() {
        // Arrange
        String accountId = "12345";
        AccountEntity mockAccountEntity = new AccountEntity("12345", "98765432100");
        when(gateway.execute(accountId)).thenReturn(Optional.of(mockAccountEntity));

        // Act
        Account result = getAccountUseCase.execute(accountId);

        // Assert
        assertNotNull(result);
        assertEquals(accountId, result.getAccountId());
        assertEquals("98765432100", result.getDocumentNumber());
        verify(gateway, times(1)).execute(accountId);
    }

    @Test
    void testExecute_InvalidAccountId_ThrowsNotFoundException() {
        // Arrange
        String accountId = "99999";
        when(gateway.execute(accountId)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> getAccountUseCase.execute(accountId));
        assertEquals("Account not found with ID: " + accountId, exception.getMessage());
        verify(gateway, times(1)).execute(accountId);
    }
}