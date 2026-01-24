package com.arya.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arya.AuthService;
import com.arya.BcryptStub;
import com.arya.PasswordEncoder;

// boot can do this by @SpringBootApplication

@Configuration //hey spring , read this and learn how to create obj
public class AppConfig {
	
	@Bean //define the dependency
	public PasswordEncoder myEncoder() {
		return new BcryptStub();
	}
	
	@Bean //define the service and inject dependency manually
	public AuthService authService() {
		return new AuthService(myEncoder());
	}
}
