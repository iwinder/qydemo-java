package com.windcoder.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SecurityFileApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(SecurityFileApplication.class,args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SecurityFileApplication.class);
	}
}
