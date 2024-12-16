package com.pismo.core.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Account {
    private String accountId;
    private String documentNumber;
    private Integer version;
}