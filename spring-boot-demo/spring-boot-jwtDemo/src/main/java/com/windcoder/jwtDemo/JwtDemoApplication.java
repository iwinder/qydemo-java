package com.windcoder.jwtDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JwtDemoApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class,args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JwtDemoApplication.class);
	}
}
