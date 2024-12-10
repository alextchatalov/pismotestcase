package com.pismo.entrypoint.domain;

import com.prismo.core.account.domain.Account;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class AccountRequest {
    private String accountId;
    private String documentNumber;

    public static AccountRequest toResponse(Account account) {
        return new AccountRequest(account.getAccountId(), account.getDocumentNumber());
    }

    public Account toDomain() {
        return Account.builder().accountId(accountId).documentNumber(documentNumber).build();
    }
}
