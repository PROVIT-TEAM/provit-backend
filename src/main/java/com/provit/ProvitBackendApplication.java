package com.provit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProvitBackendApplication{
	public static void main(String[] args) {
		SpringApplication.run(ProvitBackendApplication.class, args);
	}
}
