package com.team2.azcendapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AzcendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzcendApiApplication.class, args);
	}
}
