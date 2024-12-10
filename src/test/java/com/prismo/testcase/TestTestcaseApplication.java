package com.prismo.testcase;

import org.springframework.boot.SpringApplication;

public class TestTestcaseApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestcaseApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
