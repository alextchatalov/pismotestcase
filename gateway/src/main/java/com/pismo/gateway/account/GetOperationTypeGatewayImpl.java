package com.pismo.entrypoint.account;

import com.pismo.entrypoint.account.domain.OperationTypeEntity;
import com.pismo.entrypoint.account.repository.OperationTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetOperationTypeGatewayImpl implements GetOperationTypeGateway {

    private final OperationTypeRepository repository;

    public GetOperationTypeGatewayImpl(OperationTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<OperationTypeEntity> execute(int operationTypeId) {
        return repository.findById(operationTypeId);
    }
}
