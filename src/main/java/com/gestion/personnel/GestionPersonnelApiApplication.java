package com.gestion.personnel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class GestionPersonnelApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionPersonnelApiApplication.class, args);
	}

}
