package com.dit.hua.houseM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class HouseMApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseMApplication.class, args);
	}

}
