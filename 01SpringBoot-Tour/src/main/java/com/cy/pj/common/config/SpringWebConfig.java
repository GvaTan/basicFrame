package com.cy.pj.common.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cy.pj.common.web.TimeHandlerInterceptor;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer{
	/**
	 * 注册spring mvc 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TimeHandlerInterceptor())
		.addPathPatterns("/user/doLogin");
	}
}
