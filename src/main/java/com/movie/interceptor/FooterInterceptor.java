package com.movie.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.movie.Review.BO.ReviewBO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FooterInterceptor implements HandlerInterceptor {

	@Autowired
	private ReviewBO reviewBO;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		if (modelAndView != null) {
			int totalReviewCount = reviewBO.getTotalReviewCount();
			modelAndView.addObject("totalReviewCount", totalReviewCount);
		}
	}
	
}