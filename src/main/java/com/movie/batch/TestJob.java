package com.movie.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // 스프링 빈 (0813)
public class TestJob {
	
	// 스케줄링 거는 어노테이션 - (서버가 켜져있는 동안에) 어느 특정 시간마다 매소드가 수행하게끔 하라!
	// https://velog.io/@jay2u8809/Crontab%ED%81%AC%EB%A1%A0%ED%83%AD-%EC%8B%9C%EA%B0%84-%EC%84%A4%EC%A0%95 참고 사이트
	@Scheduled(cron = "0 */1 * * * *") // 매 1분마다 실행
	public void task() {
		log.info("####$$$$ task batch 수행!!!!"); // 서버 키면 1분마다 콘솔창에 뜬다.(추가가 돼서 쌓이게 된다.)
	}
}
