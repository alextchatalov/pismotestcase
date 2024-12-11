package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.AccountEntity;
import com.pismo.gateway.account.domain.OperationTypeEntity;
import com.pismo.gateway.account.domain.TransactionEntity;
import com.pismo.gateway.account.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateTransactionGatewayImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private CreateTransactionGatewayImpl createTransactionGateway;

    public CreateTransactionGatewayImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_ShouldSaveAndReturnTransactionEntity() {
        // Arrange
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .transactionId("123")
                .account(new AccountEntity("123", "1342")) // Assuming AccountEntity has a constructor with id
                .operationType(new OperationTypeEntity(1, "Test")) // Assuming OperationTypeEntity has a constructor with id
                .amount(new BigDecimal("100.00"))
                .eventDate(LocalDateTime.now())
                .build();
        when(transactionRepository.save(any())).thenReturn(transactionEntity);
        // Act
        TransactionEntity result = createTransactionGateway.execute(transactionEntity);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getTransactionId()).isNotNull();
        assertThat(result.getAmount()).isEqualTo(transactionEntity.getAmount());
        assertThat(result.getAccount()).isEqualTo(transactionEntity.getAccount());
        assertThat(result.getOperationType()).isEqualTo(transactionEntity.getOperationType());
    }
}