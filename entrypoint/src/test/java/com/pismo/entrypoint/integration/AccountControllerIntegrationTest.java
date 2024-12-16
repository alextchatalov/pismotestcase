package com.pismo.entrypoint.integration;

import com.pismo.entrypoint.config.IntegrationTestConfig;
import com.pismo.entrypoint.domain.AccountRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountControllerIntegrationTest extends IntegrationTestConfig {

    @Test
    public void testCreateAccountAndFetchAccount() {
        // Step 1: Create an Account
        AccountRequest createRequest = new AccountRequest("12345", "12345");
        ResponseEntity<AccountRequest> createResponse = getRestTemplate().postForEntity(
                "http://localhost:" + getPort() + "/accounts",
                createRequest,
                AccountRequest.class);

        // Assertions for Account creation
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();
        assertThat(createResponse.getBody().getAccountId()).isNotNull();
        assertThat(createResponse.getBody().getDocumentNumber()).isEqualTo("12345");
        String accountId = createResponse.getBody().getAccountId();

        // Step 2: Get the Account
        ResponseEntity<AccountRequest> getResponse = getRestTemplate().getForEntity(
                "http://localhost:" + getPort() + "/accounts/"+ accountId,
                AccountRequest.class);

        // Assertions for fetching Account
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().getAccountId()).isEqualTo(accountId);
        assertThat(getResponse.getBody().getDocumentNumber()).isEqualTo("12345");
    }

    @Test
    public void testGetAccountNotFound() {
        String nonExistentAccountId = "99999";

        ResponseEntity<AccountRequest> getResponse = getRestTemplate().getForEntity(
                "http://localhost:" + getPort() + "/accounts/" + nonExistentAccountId,
                AccountRequest.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}