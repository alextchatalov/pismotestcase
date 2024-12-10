package com.pismo.entrypoint;

import com.pismo.core.transaction.domain.Transaction;
import com.pismo.entrypoint.domain.TransactionRequest;
import com.pismo.core.transaction.CreateTransactionUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    
    private final CreateTransactionUseCase createTransactionUseCase;

    @Autowired
    public TransactionController(CreateTransactionUseCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }


    @PostMapping("/transactions")
    public ResponseEntity<TransactionRequest> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = createTransactionUseCase.execute(transactionRequest.toDomain());
        return new ResponseEntity<>(TransactionRequest.toResponse(transaction), HttpStatus.CREATED);
    }
}
