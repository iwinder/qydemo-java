package com.windcoder.updateFile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationMvcConfig  extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(FileUploadProperties.VIRTUALPATH + "/**")
				.addResourceLocations("file:" + FileUploadProperties.CONTENTPATH + "/");
//                .setCacheControl(CacheControl.maxAge(-1, TimeUnit.SECONDS).cachePublic());
	}
}
