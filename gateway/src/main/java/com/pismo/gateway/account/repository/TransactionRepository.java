package com.pismo.gateway.account.repository;

import com.pismo.gateway.account.domain.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<AccountEntity, String> {

    AccountEntity findByDocumentNumber(String documentNumber);
}
