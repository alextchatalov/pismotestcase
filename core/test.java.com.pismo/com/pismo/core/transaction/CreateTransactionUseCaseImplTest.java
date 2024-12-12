package com.pismo.core.transaction;

import com.pismo.core.account.GetAccountUseCase;
import com.pismo.core.account.domain.Account;
import com.pismo.core.exception.NotFoundException;
import com.pismo.core.operationType.GetOperationTypeUseCase;
import com.pismo.core.operationType.OperationType;
import com.pismo.core.transaction.domain.Transaction;
import com.pismo.gateway.account.CreateTransactionGateway;
import com.pismo.gateway.account.domain.AccountEntity;
import com.pismo.gateway.account.domain.OperationTypeEntity;
import com.pismo.gateway.account.domain.TransactionEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateTransactionUseCaseImplTest {

    @Mock
    private CreateTransactionGateway gateway;

    @Mock
    private GetAccountUseCase getAccountUseCase;

    @Mock
    private GetOperationTypeUseCase getOperationTypeUseCase;

    @InjectMocks
    private CreateTransactionUseCaseImpl createTransactionUseCaseImpl;

    CreateTransactionUseCaseImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_ShouldReturnTransaction_WhenValidTransactionProvided() {
        // Arrange
        Transaction transaction = Transaction.builder()
                .account(Account.builder().accountId("1").documentNumber("12345678901").build())
                .operationType(OperationType.builder().operationTypeId(1).description("Purchase").build())
                .amount(new BigDecimal("100.00"))
                .build();

        AccountEntity accountEntity = new AccountEntity("1", "12345678901");
        OperationTypeEntity operationTypeEntity = new OperationTypeEntity(1, "Purchase");

        TransactionEntity transactionEntity = TransactionEntity.builder()
                .account(accountEntity)
                .operationType(operationTypeEntity)
                .amount(new BigDecimal("100.00"))
                .eventDate(LocalDateTime.now())
                .build();

        when(getAccountUseCase.execute("1")).thenReturn(new Account("1", "12345678901"));
        when(getOperationTypeUseCase.execute(1)).thenReturn(OperationType.builder().operationTypeId(operationTypeEntity.getId()).description(operationTypeEntity.getDescription()).build());
        when(gateway.execute(any())).thenReturn(transactionEntity);

        // Act
        Transaction result = createTransactionUseCaseImpl.execute(transaction);

        // Assert
        assertEquals("1", result.getAccount().getAccountId());
        assertEquals("12345678901", result.getAccount().getDocumentNumber());
        assertEquals(1, result.getOperationType().getOperationTypeId());
        assertEquals("Purchase", result.getOperationType().getDescription());
        assertEquals(new BigDecimal("100.00"), result.getAmount());

        verify(getAccountUseCase).execute("1");
        verify(getOperationTypeUseCase).execute(1);
        verify(gateway).execute(any(TransactionEntity.class));
    }

    @Test
    void execute_ShouldThrowNotFoundException_WhenAccountNotFound() {
        // Arrange
        Transaction transaction = Transaction.builder()
                .account(Account.builder().accountId("1").build())
                .build();

        when(getAccountUseCase.execute("1")).thenReturn(null);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> createTransactionUseCaseImpl.execute(transaction));

        verify(getAccountUseCase).execute("1");
        verifyNoInteractions(getOperationTypeUseCase, gateway);
    }

    @Test
    void execute_ShouldHandleNullOperationType() {
        // Arrange
        Transaction transaction = Transaction.builder()
                .account(Account.builder().accountId("1").documentNumber("12345678901").build())
                .operationType(OperationType.builder().operationTypeId(1).build())
                .amount(new BigDecimal("100.00"))
                .build();

        when(getAccountUseCase.execute("1")).thenReturn(new Account("1", "12345678901"));
        when(getOperationTypeUseCase.execute(1)).thenReturn(null);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> createTransactionUseCaseImpl.execute(transaction));

        verify(getAccountUseCase).execute("1");
        verify(getOperationTypeUseCase).execute(1);
    }
}