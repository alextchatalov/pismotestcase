package com.pismo.entrypoint.integration;

import com.pismo.core.account.CreateAccountUseCase;
import com.pismo.core.account.domain.Account;
import com.pismo.entrypoint.config.IntegrationTestConfig;
import com.pismo.entrypoint.domain.TransactionRequest;
import com.pismo.entrypoint.domain.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
@Sql(statements = {
        "INSERT INTO operation_type (operation_type_id, description) VALUES (1, 'COMPRA A VISTA')," +
                "(2, 'COMPRA PARCELADA')," +
                "(3, 'SAQUE')," +
                "(4, 'PAGAMENTO')"
})
public class TransactionControllerIntegrationTest extends IntegrationTestConfig {

    @Autowired
    private CreateAccountUseCase createAccountUseCase;
    private Account accountSaved;

    @BeforeEach
    public void setUp() {
        Account account = new Account("123","123", 0);
        accountSaved = createAccountUseCase.execute(account);
    }

    @Test
    public void testCreateTransaction() {
        // Step 1: Create a Transaction
        TransactionRequest createRequest = new TransactionRequest(accountSaved.getAccountId(), 1, new BigDecimal(200));
        ResponseEntity<TransactionResponse> createResponse = getRestTemplate().postForEntity(
                "http://localhost:" + getPort() + "/transactions",
                createRequest,
                TransactionResponse.class);

        // Assertions for Transaction creation
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();
        assertThat(createResponse.getBody().getAccount().getAccountId()).isEqualTo(accountSaved.getAccountId());
        assertThat(createResponse.getBody().getAccount().getDocumentNumber()).isEqualTo(accountSaved.getDocumentNumber());
        assertThat(createResponse.getBody().getOperationType().getOperationTypeId()).isEqualTo(1);
        assertThat(createResponse.getBody().getOperationType().getDescription()).isEqualTo("COMPRA A VISTA");

    }
}
