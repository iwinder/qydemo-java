package com.windcoder.cloudCoueseDemo.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 增加 @EnableEurekaClient 注解
 */
@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class GatewayApplication {


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GatewayApplication.class);
		Environment env = app.run(args).getEnvironment();
		log.info("启动成功！！");
		log.info("Gateway地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));


	}

	/**
	 *  因为前端通过gateway访问，所以在这里配置允许跨域请求。
	 * @return
	 */
	@Bean
	public CorsWebFilter corsWebFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(Boolean.TRUE);
		config.addAllowedMethod("*");
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
		source.registerCorsConfiguration("/**", config);

		return new CorsWebFilter(source);
	}
}
