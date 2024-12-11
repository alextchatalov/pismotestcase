package com.pismo.entrypoint;

import com.pismo.core.account.CreateAccountUseCase;
import com.pismo.core.account.domain.Account;
import com.pismo.entrypoint.domain.AccountRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateAccountUseCase createAccountUseCase;

    @Test
    @DisplayName("Should successfully create an account and return HTTP 201")
    void createAccount_Success() throws Exception {
        AccountRequest accountRequest = AccountRequest.builder()
                .accountId("1")
                .documentNumber("12345678900")
                .build();

        Account mockAccount = Account.builder()
                .accountId("1")
                .documentNumber("12345678900")
                .build();

        when(createAccountUseCase.execute(any())).thenReturn(mockAccount);

        String requestBody = """
                {
                    "account_id": "1",
                    "document_number": "12345678900"
                }
                """;

        String expectedResponseBody = """
                {
                    "account_id": "1",
                    "document_number": "12345678900"
                }
                """;

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResponseBody));

        Mockito.verify(createAccountUseCase).execute(any());
    }

    @Test
    @DisplayName("Should return HTTP 400 when document number is empty")
    void createAccount_EmptyDocumentNumber() throws Exception {
        String requestBody = """
                {
                    "account_id": "1",
                    "document_number": ""
                }
                """;

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Document number must not be null or empty")));
    }

    @Test
    @DisplayName("Should return HTTP 400 when no document number is provided")
    void createAccount_MissingDocumentNumber() throws Exception {
        String requestBody = """
                {
                    "account_id": "1"
                }
                """;

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Document number must not be null or empty")));
    }
}