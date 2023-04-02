package com.windcoder.springCloudEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
//@RestController
public class EurekaApplication {
//	@RequestMapping("/")
//	public String home() {
//		return "Hello World";
//	}
	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}
}
