package com.pismo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.pismo.gateway", "com.pismo.entrypoint", "com.pismo.core"})
public class TestcaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcaseApplication.class, args);
	}

}
