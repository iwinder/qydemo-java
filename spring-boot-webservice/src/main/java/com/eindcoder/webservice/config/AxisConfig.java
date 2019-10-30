package com.eindcoder.webservice.config;

import org.apache.axis2.transport.http.AxisServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxisConfig {
	@Bean
	public ServletRegistrationBean axisServlet(){
		ServletRegistrationBean axisServlet = new ServletRegistrationBean();
		axisServlet.setServlet(new AxisServlet());
		axisServlet.addUrlMappings("/services/*");
		// 设置服务路径，主要用于读取生成的services.xml文件，注意这里的serviesPath为保持和resouse下文件名一致
		String path = this.getClass().getResource("/webservice").getPath();
		axisServlet.addInitParameter("axis2.repository.path", path);
		axisServlet.setLoadOnStartup(1);

		return axisServlet;
	}
}
