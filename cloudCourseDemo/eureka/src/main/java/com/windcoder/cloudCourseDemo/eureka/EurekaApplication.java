package com.windcoder.cloudCourseDemo.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

/**
 * 增加注解 @EnableEurekaServer 自动作为注册中心
 *
 */
@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekaApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(EurekaApplication.class, args);
//
//
//	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(EurekaApplication.class);
		Environment env = app.run(args).getEnvironment();
		log.info("启动成功！！");
		log.info("Eureka地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));


	}

}
