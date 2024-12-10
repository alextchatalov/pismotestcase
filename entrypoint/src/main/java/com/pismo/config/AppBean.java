package com.pismo.config;

import com.prismo.core.account.CreateAccountUseCase;
import com.prismo.core.account.CreateAccountUseCaseImpl;
import com.prismo.core.account.GetAccountUseCase;
import com.prismo.core.account.GetAccountUseCaseImpl;
import com.prismo.core.transaction.CreateTransactionUseCase;
import com.prismo.core.transaction.CreateTransactionUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBean {
    @Bean
    public CreateAccountUseCase createAccountUseCase() {
        return new CreateAccountUseCaseImpl();
    }

    @Bean
    public GetAccountUseCase getAccountUseCase() {return new GetAccountUseCaseImpl();}

    @Bean
    public CreateTransactionUseCase createTransactionUseCase() {return new CreateTransactionUseCaseImpl();}
}
