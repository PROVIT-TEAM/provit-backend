package com.provit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.provit.domain.User;
import com.provit.domain.UserRepository;
import com.provit.domain.UserRole;
import com.provit.domain.UserRoleRepository;

@SpringBootApplication
public class ProvitBackendApplication implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(ProvitBackendApplication.class);
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProvitBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRoleRepository.save(new UserRole("ROLE_ADMIN"));
		UserRole userRole = userRoleRepository.findByName("ROLE_ADMIN");
		userRepository.save(new User("user", "$2a$12$rPbYY45ynWMnPnO0OHD9HutXORPuTVrjmUU8F.Q00EecRpYNn6o46", userRole));
	}
	
}
