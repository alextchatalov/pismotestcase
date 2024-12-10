package com.prismo.core.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public class Account {
    private String accountId;
    private String documentNumber;
}