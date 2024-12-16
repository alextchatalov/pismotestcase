package com.pismo.gateway;

import com.pismo.gateway.account.CreateAccountGatewayImpl;
import com.pismo.gateway.account.domain.AccountEntity;
import com.pismo.gateway.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateAccountGatewayImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private CreateAccountGatewayImpl createAccountGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_shouldCallSaveWithCorrectEntity() {
        // Arrange
        AccountEntity mockAccount = new AccountEntity("test");

        when(accountRepository.save(any(AccountEntity.class))).thenReturn(mockAccount);

        // Act
        AccountEntity result = createAccountGateway.execute(mockAccount);

        // Assert
        ArgumentCaptor<AccountEntity> captor = ArgumentCaptor.forClass(AccountEntity.class);
        verify(accountRepository, times(1)).save(captor.capture());
        assertEquals(mockAccount, captor.getValue());
        assertNotNull(result);
        assertEquals(mockAccount.getAccountId(), result.getAccountId());
        assertEquals(mockAccount.getDocumentNumber(), result.getDocumentNumber());
    }

    @Test
    void execute_shouldReturnSavedAccountEntity() {
        // Arrange
        AccountEntity inputAccount = new AccountEntity( "test");

        AccountEntity savedAccount = new AccountEntity("test");

        when(accountRepository.save(inputAccount)).thenReturn(savedAccount);

        // Act
        AccountEntity result = createAccountGateway.execute(inputAccount);

        // Assert
        assertNotNull(result);
        assertEquals(savedAccount.getAccountId(), result.getAccountId());
        assertEquals(savedAccount.getDocumentNumber(), result.getDocumentNumber());
    }
}
