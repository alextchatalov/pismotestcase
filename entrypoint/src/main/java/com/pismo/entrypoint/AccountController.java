package com.pismo.entrypoint;

import com.pismo.core.account.CreateAccountUseCase;
import com.pismo.core.account.GetAccountUseCase;
import com.pismo.core.account.domain.Account;
import com.pismo.gateway.account.domain.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pismo.entrypoint.domain.AccountRequest;

@RestController
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountUseCase getAccountUseCase;

    @Autowired
    public AccountController(CreateAccountUseCase createAccountUseCase, GetAccountUseCase getAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.getAccountUseCase = getAccountUseCase;
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountRequest> createAccount(@RequestBody AccountRequest accountRequest) {
        Account account = createAccountUseCase.execute(accountRequest.toDomain());
        return new ResponseEntity<>(AccountRequest.toResponse(account), HttpStatus.CREATED);
    }

    @GetMapping("/accounts/{accountId}")
    public AccountRequest getAccountInfo(@PathVariable String accountId) {
        Account account = getAccountUseCase.execute(accountId);
        return AccountRequest.toResponse(account);
    }
}
