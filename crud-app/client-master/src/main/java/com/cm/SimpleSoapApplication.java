package com.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebServiceConfig.class})
public class SimpleSoapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSoapApplication.class, args);
	}
}
