
package com.pismo.core.operationType;

import com.pismo.core.exception.NotFoundException;
import com.pismo.gateway.account.GetOperationTypeGateway;
import com.pismo.gateway.account.domain.OperationTypeEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetOperationTypeUseCaseImpl implements GetOperationTypeUseCase {
    static final String OPERATION_TYPE_NOT_FOUND_MESSAGE = "Operation Type not found with ID: ";

    private final RedisTemplate<Object, Object> redisTemplate;
    private final GetOperationTypeGateway getOperationTypeGateway;

    public GetOperationTypeUseCaseImpl(GetOperationTypeGateway getOperationTypeGateway, RedisTemplate<Object, Object> redisTemplate) {
        this.getOperationTypeGateway = getOperationTypeGateway;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public OperationType execute(int operationTypeId) {
        return getOperationTypeFromRedis(operationTypeId).orElseThrow(() ->
                new NotFoundException(OPERATION_TYPE_NOT_FOUND_MESSAGE + operationTypeId));
    }

    private Optional<OperationType> getOperationTypeFromRedis(int operationTypeId) {
        return getFromRedis(operationTypeId).or(() -> fetchFromGatewayAndCache(operationTypeId));
    }

    private Optional<OperationType> getFromRedis(int operationTypeId) {
        return Optional.ofNullable((OperationType) redisTemplate.opsForValue().get(operationTypeId));
    }

    private Optional<OperationType> fetchFromGatewayAndCache(int operationTypeId) {
        Optional<OperationTypeEntity> operationTypeEntity = getOperationTypeGateway.execute(operationTypeId);
        if (operationTypeEntity.isEmpty()) {
            return Optional.empty();
        }
        OperationType operationType = OperationType.builder()
                .operationTypeId(operationTypeEntity.get().getId())
                .description(operationTypeEntity.get().getDescription())
                .build();
        redisTemplate.opsForValue().set(operationTypeId, operationType);
        return Optional.of(operationType);
    }
}
