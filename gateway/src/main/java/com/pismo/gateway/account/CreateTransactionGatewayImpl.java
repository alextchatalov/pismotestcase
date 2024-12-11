package com.pismo.entrypoint.account;

import com.pismo.entrypoint.account.domain.TransactionEntity;
import com.pismo.entrypoint.account.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionGatewayImpl implements CreateTransactionGateway {

    private final TransactionRepository repository;

    public CreateTransactionGatewayImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransactionEntity execute(TransactionEntity transactionEntity) {
        return repository.save(transactionEntity);
    }
}
