package com.movie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.movie.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private PermissionInterceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(interceptor)
		.addPathPatterns("/**")
		.excludePathPatterns();
	}
}
