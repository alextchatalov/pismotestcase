package com.pismo.entrypoint.account.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "transaction")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_id", updatable = false, nullable = false, unique = true)
    private String transactionId;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "account_ID")
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "operation_type_id", nullable = false, referencedColumnName = "operation_type_id")
    private OperationTypeEntity operationType;

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;
}