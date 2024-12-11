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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final CreateTransactionGateway gateway;
    private final GetAccountUseCase getAccountUseCase;
    private final GetOperationTypeUseCase getOperationTypeUseCase;

    public CreateTransactionUseCaseImpl(CreateTransactionGateway gateway, GetAccountUseCase getAccountUseCase, GetOperationTypeUseCase getOperationTypeUseCase) {
        this.gateway = gateway;
        this.getAccountUseCase = getAccountUseCase;
        this.getOperationTypeUseCase = getOperationTypeUseCase;
    }

    @Override
    public Transaction execute(Transaction transaction) {
        TransactionEntity transactionEntity = createTransactionEntity(transaction);
        TransactionEntity savedTransaction = gateway.execute(transactionEntity);
        return buildTransaction(savedTransaction);
    }

    private static Transaction buildTransaction(TransactionEntity savedTransaction) {
        return Transaction.builder()
                .account(Account.builder()
                        .accountId(savedTransaction.getAccount().getAccountId())
                        .documentNumber(savedTransaction.getAccount().getDocumentNumber())
                        .build())
                .amount(savedTransaction.getAmount())
                .operationType(
                        OperationType.builder()
                                .operationTypeId(savedTransaction.getOperationType().getId())
                                .description(savedTransaction.getOperationType().getDescription())
                                .build())
                .build();
    }

    private TransactionEntity createTransactionEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .account(getAccount(transaction))
                .operationType(getOperationType(transaction))
                .amount(transaction.getAmount())
                .eventDate(LocalDateTime.now())
                .build();
    }

    private OperationTypeEntity getOperationType(Transaction transaction) {
        return getOperationTypeUseCase.execute(transaction.getOperationType().getOperationTypeId());
    }

    private AccountEntity getAccount(Transaction transaction) {
        Account account = getAccountUseCase.execute(transaction.getAccount().getAccountId());
        if (account == null) {
            throw new NotFoundException("Account not found with ID: " + transaction.getAccount().getAccountId());
        }
        return new AccountEntity(account.getAccountId(), account.getDocumentNumber());
    }
}
