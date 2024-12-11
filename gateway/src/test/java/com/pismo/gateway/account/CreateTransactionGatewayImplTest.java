package com.pismo.gateway.account;

import com.pismo.gateway.account.domain.TransactionEntity;
import com.pismo.gateway.account.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CreateTransactionGatewayImplTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CreateTransactionGatewayImpl createTransactionGateway;

    @Test
    public void testExecute_ShouldSaveAndReturnTransactionEntity() {
        // Arrange
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .account(new AccountEntity("123")) // Assuming AccountEntity has a constructor with id
                .operationType(new OperationTypeEntity(1)) // Assuming OperationTypeEntity has a constructor with id
                .amount(new BigDecimal("100.00"))
                .eventDate(LocalDateTime.now())
                .build();

        // Act
        TransactionEntity result = createTransactionGateway.execute(transactionEntity);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getTransactionId()).isNotNull();
        assertThat(result.getAmount()).isEqualTo(transactionEntity.getAmount());
        assertThat(result.getAccount()).isEqualTo(transactionEntity.getAccount());
        assertThat(result.getOperationType()).isEqualTo(transactionEntity.getOperationType());
    }

    @Test
    public void testExecute_ShouldThrowException_WhenTransactionEntityIsInvalid() {
        // Arrange
        TransactionEntity invalidTransactionEntity = TransactionEntity.builder()
                .amount(null)
                .eventDate(null)
                .build();

        // Act & Assert
        assertThrows(Exception.class, () -> createTransactionGateway.execute(invalidTransactionEntity));
    }
}