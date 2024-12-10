package com.pismo.entrypoint.domain;

import com.prismo.core.account.domain.Account;
import lombok.*;

@Builder
@AllArgsConstructor
public class AccountRequest {
    private String accountId;
    private String documentNumber;

    public static AccountRequest toResponse(Account account) {
        return AccountRequest.builder().accountId(account.getAccountId()).documentNumber(account.getDocumentNumber()).build();
    }

    public Account toDomain() {
        return Account.builder().accountId(accountId).documentNumber(documentNumber).build();
    }
}
