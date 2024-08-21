package com.movie.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle() {
		
	}
	
}
