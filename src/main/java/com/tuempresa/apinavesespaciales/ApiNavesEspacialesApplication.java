package com.tuempresa.apinavesespaciales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiNavesEspacialesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiNavesEspacialesApplication.class, args);
	}

}
