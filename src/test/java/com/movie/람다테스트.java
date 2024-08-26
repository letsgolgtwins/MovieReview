package com.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class 람다테스트 {

	@Test
	void 람다테스트1() {
		// 람다, Stream 
		List<String> fruits = List.of("apple", "banana", "cherry");
		fruits
		.stream()
		.filter(element -> element.startsWith("b")) // b로 시작하는 것만 필터링 하겠다.
		.forEach(element -> log.info("######## {}", element)); // 로그로 결과 찍어보기
	}
	
	@Test
	void 람다테스트2() { // 리스트의 요소들을 대문자로 바꿔서 저장하는 거
		List<String> fruits = List.of("apple", "banana", "cherry");
		
		fruits = fruits // 다시 저장
		.stream()
		.map(element -> element.toUpperCase()) // 요소들을 하나하나 순회하면서 대문자로 바꿔놓는다
		.collect(Collectors.toList()); // stream을 List로 변환하는 과정
	
		log.info("$$$$$$$$ {}", fruits); // 로그로 결과 찍어보기
	}
	
	@Test // 0826
	void 메소드레퍼런스() {
		List<String> fruits = List.of("apple", "banana", "cherry");
		
		fruits = fruits
		.stream()
		.map(String::toUpperCase) // element -> element.toUpperCase() 람다식을 변환
		.collect(Collectors.toList());
	
		log.info("%%%%%%%%% {}", fruits);
	}
}
