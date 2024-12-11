package com.pismo.entrypoint.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pismo.core.account.domain.Account;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Builder
@AllArgsConstructor
@Getter
public class AccountRequest {
    @JsonProperty("account_id")
    private String accountId;
    @JsonProperty("document_number")
    @NotBlank(message = "Document number must not be null or empty")
    private String documentNumber;

    public static AccountRequest toResponse(Account account) {
        return AccountRequest.builder().accountId(account.getAccountId()).documentNumber(account.getDocumentNumber()).build();
    }

    public Account toDomain() {
        return Account.builder().accountId(accountId).documentNumber(documentNumber).build();
    }
}
