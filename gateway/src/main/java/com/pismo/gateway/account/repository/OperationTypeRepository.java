package com.pismo.gateway.account.repository;

import com.pismo.gateway.account.domain.OperationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationTypeEntity, Integer> {
}
