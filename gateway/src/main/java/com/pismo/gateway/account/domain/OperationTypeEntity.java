package com.pismo.gateway.account.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operation_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationTypeEntity {

    @Id
    @Column(name = "operation_type_id")
    private int id;
    private String description;
}
