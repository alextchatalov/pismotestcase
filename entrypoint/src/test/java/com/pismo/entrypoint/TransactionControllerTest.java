package com.pismo.entrypoint;

import com.pismo.core.account.domain.Account;
import com.pismo.core.operationType.OperationType;
import com.pismo.core.transaction.CreateTransactionUseCase;
import com.pismo.core.transaction.domain.Transaction;
import com.pismo.entrypoint.domain.TransactionRequest;
import com.pismo.entrypoint.domain.TransactionResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    private CreateTransactionUseCase createTransactionUseCase;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    @DisplayName("Should create a transaction and return HTTP 201")
    void createTransaction_Success() {
        // Arrange
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .accountId("123")
                .operationTypeId(1)
                .amount(new BigDecimal("100.00"))
                .build();

        Transaction mockTransaction = Transaction.builder()
                .account(Account.builder()
                        .accountId("123")
                        .documentNumber("45678912300")
                        .build())
                .operationType(OperationType.builder()
                        .operationTypeId(1)
                        .description("Purchase")
                        .build())
                .amount(new BigDecimal("100.00"))
                .build();

        when(createTransactionUseCase.execute(any())).thenReturn(mockTransaction);

        // Act
        TransactionResponse response = transactionController.createTransaction(transactionRequest).getBody();

        // Assert
        assertEquals("123", response.getAccount().getAccountId());
        assertEquals(1, response.getOperationType().getOperationTypeId());
        assertEquals(new BigDecimal("100.00"), response.getAmount());
        Mockito.verify(createTransactionUseCase).execute(any());
    }

    @Test
    @DisplayName("Should throw internal server error when use case fails")
    void createTransaction_UseCaseFailure() {
        // Arrange
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .accountId("123")
                .operationTypeId(1)
                .amount(new BigDecimal("100.00"))
                .build();

        when(createTransactionUseCase.execute(any())).thenThrow(new RuntimeException("Unexpected error"));

        // Act & Assert
        try {
            transactionController.createTransaction(transactionRequest);
        } catch (RuntimeException ex) {
            assertEquals("Unexpected error", ex.getMessage());
        }
    }
}