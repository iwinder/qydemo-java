package com.windcoder.updateFile.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "windcoder.ftp")
@Data
public class WdFtpProperties {
	private String hostname;
	private Integer port;
	private String username;
	private String password;
	private String home = "";
	private String cachePath;
	private String encoding;
}
