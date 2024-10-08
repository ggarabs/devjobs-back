package com.ggarabetti.devjobs_crud;

import org.springframework.boot.SpringApplication;

public class TestDevjobsCrudApplication {

	public static void main(String[] args) {
		SpringApplication.from(DevjobsCrudApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
