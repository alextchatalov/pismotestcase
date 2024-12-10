package com.pismo.gateway.account.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String accountId;

    private String documentNumber;
}
