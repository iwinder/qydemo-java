package com.windcoder.cloudCourseDemo.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

/**
 * 增加 @EnableEurekaClient 注解
 */
@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class SystemApplication {


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SystemApplication.class);
		Environment env = app.run(args).getEnvironment();
		log.info("启动成功！！");
		log.info("System地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));


	}

}
