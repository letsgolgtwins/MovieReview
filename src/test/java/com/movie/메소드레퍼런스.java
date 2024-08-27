package com.movie;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class 메소드레퍼런스 {

	@ToString
	@AllArgsConstructor
	public class Person {
		private String name; // 이름
		private int age; // 나이
	
		public void printInfo() {
			log.info("$$$$$$$$ {}", this);
		}
	}
	
	@Test
	void 메소드레퍼런스테스트() {
		List<Person> people = new ArrayList<>();
		people.add(new Person("오지환", 10));
		people.add(new Person("홍창기", 51));
		
		// 객체 안에 있는 메소드 호출
		//people.forEach(p -> p.printInfo()); // 방법1. 람다식
		//people.forEach(Person::printInfo); // 방법2. 메소드 레퍼런스
		
		// 객체를 println으로 출력
		//people.forEach(p -> System.out.println(p)); // 방법1. 람다식
		people.forEach(System.out::println); // 방법2. 메소드 레퍼런스 방식
	
	}
}
