package com.movie.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect // 부가기능을 정의(advice) + 어디에 적용할 건지(pointcut) 
@Component // Spring bean이지만 service도 아니고 controller도 아닐 땐, component 어노테이션
public class TimeTraceAOP {
	
	//@Around("execution(* com.movie..*(..))") // 패키지의 범위 => 어디에 적용할 건지(pointcut)
	@Around("@annotation(TimeTrace)") // 어느 어노테이션이 붙어있을 때 수행할 건지
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		// 시간 측정
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object proceedObj = joinPoint.proceed(); // 본래 할 일을 수행하는 것.
		
		sw.stop(); // 멈춤
		
		log.info("$$$$$$$$$ 실행시간(ms) : " + sw.getTotalTimeMillis());
		log.info(sw.prettyPrint());
		return proceedObj;
		
	}
}
