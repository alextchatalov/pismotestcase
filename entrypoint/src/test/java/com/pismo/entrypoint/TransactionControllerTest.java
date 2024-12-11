package com.pismo.entrypoint;

import com.pismo.core.transaction.CreateTransactionUseCase;
import com.pismo.core.transaction.domain.Transaction;
import com.pismo.entrypoint.domain.TransactionRequest;
import com.pismo.entrypoint.domain.TransactionResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateTransactionUseCase createTransactionUseCase;

    @Test
    void createTransaction_shouldReturnCreatedStatus_whenValidRequest() throws Exception {
        Transaction mockTransaction = Transaction.builder()
                .id(1L)
                .accountId("123")
                .operationTypeId(1)
                .amount(new BigDecimal("100.00"))
                .build();

        Mockito.when(createTransactionUseCase.execute(any(Transaction.class))).thenReturn(mockTransaction);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"account_id\":\"123\", \"operation_type_id\":1, \"amount\":100.00}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.account_id").value("123"))
                .andExpect(jsonPath("$.operation_type_id").value(1))
                .andExpect(jsonPath("$.amount").value(100.00));
    }

    @Test
    void createTransaction_shouldReturnBadRequest_whenInvalidRequest() throws Exception {
        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createTransaction_shouldReturnInternalServerError_whenUseCaseFails() throws Exception {
        Mockito.when(createTransactionUseCase.execute(any(Transaction.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"account_id\":\"123\", \"operation_type_id\":1, \"amount\":100.00}"))
                .andExpect(status().isInternalServerError());
    }
}