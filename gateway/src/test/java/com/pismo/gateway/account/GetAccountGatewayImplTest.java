package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.AccountEntity;
import com.pismo.gateway.account.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class GetAccountGatewayImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private GetAccountGatewayImpl getAccountGatewayImpl;

    public GetAccountGatewayImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return account entity when accountId exists")
    void testExecuteWhenAccountExists() {
        // Arrange
        String accountId = "123e4567-e89b-12d3-a456-426614174000";
        AccountEntity accountEntity = new AccountEntity(accountId, "Test");
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(accountEntity));

        // Act
        Optional<AccountEntity> result = getAccountGatewayImpl.execute(accountId);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getAccountId()).isEqualTo(accountId);
    }

    @Test
    @DisplayName("Should return empty when accountId does not exist")
    void testExecuteWhenAccountDoesNotExist() {
        // Arrange
        String accountId = "non-existent-id";
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act
        Optional<AccountEntity> result = getAccountGatewayImpl.execute(accountId);

        // Assert
        assertThat(result).isEmpty();
    }
}