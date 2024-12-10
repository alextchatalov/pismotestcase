package com.pismo.core.transaction;

import com.pismo.core.account.GetAccountUseCaseImpl;
import com.pismo.core.account.domain.Account;
import com.pismo.core.exception.NotFoundException;
import com.pismo.core.operationType.GetOperationTypeUseCase;
import com.pismo.core.operationType.OperationType;
import com.pismo.core.transaction.domain.Transaction;
import com.pismo.gateway.account.CreateTransactionGatewayImpl;
import com.pismo.gateway.account.domain.AccountEntity;
import com.pismo.gateway.account.domain.OperationTypeEntity;
import com.pismo.gateway.account.domain.TransactionEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final CreateTransactionGatewayImpl gateway;
    private final GetAccountUseCaseImpl getAccountUseCase;
    private final GetOperationTypeUseCase getOperationTypeUseCase;

    public CreateTransactionUseCaseImpl(CreateTransactionGatewayImpl gateway, GetAccountUseCaseImpl getAccountUseCase, GetOperationTypeUseCase getOperationTypeUseCase) {
        this.gateway = gateway;
        this.getAccountUseCase = getAccountUseCase;
        this.getOperationTypeUseCase = getOperationTypeUseCase;
    }

    @Override
    public Transaction execute(Transaction transaction) {
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .account(getAccount(transaction))
                .operationType(getOperationType(transaction))
                .amount(transaction.getAmount())
                .eventDate(LocalDateTime.now())
                .build();
        TransactionEntity savedTransaction = gateway.execute(transactionEntity);
        return Transaction.builder()
                .accountId(savedTransaction.getAccount().getAccountId())
                .amount(savedTransaction.getAmount())
                .operationType(
                        OperationType.builder()
                                .operationTypeId(savedTransaction.getOperationType().getId())
                                .description(savedTransaction.getOperationType().getDescription())
                                .build())
                .build();
    }

    private OperationTypeEntity getOperationType(Transaction transaction) {
        return getOperationTypeUseCase.execute(transaction.getOperationType().getOperationTypeId());
    }

    private AccountEntity getAccount(Transaction transaction) {
        Account account = getAccountUseCase.execute(transaction.getAccountId());
        if (account == null) {
            throw new NotFoundException("Account not found with ID: " + transaction.getAccountId());
        }
        return new AccountEntity(account.getAccountId(), account.getDocumentNumber());
    }
}
