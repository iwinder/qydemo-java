package com.windcoder.springBootKafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootKafkaApplication  extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaApplication.class,args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootKafkaApplication.class);
	}
}
