package com.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lombok.Getter;

public class EnumTest1 {

	@Getter
	// 지금은 이 안에 만들지만, 나중엔 domain 패키지 안에 따로 new Enum파일로 만들면 된다.
	public enum Status {
		// 열거형 정의
		Y(1, true),
		N(0, false);
		
		// enum에 정의된 항목 필드
		private int value1;
		private boolean value2;
		
		// 생성자
		Status(int value1, boolean value2) {
			this.value1 = value1; // 이 value1에 밖에서 받아온 value1을 집어넣겠다.
			this.value2 = value2; 
		}
	}
	
	// 테스트 메소드
	@Test
	void status테스트() {
		// given - 준비
		Status status = Status.Y;
		
		// when - 실행
		int value1 = status.getValue1();
		boolean value2 = status.isValue2(); // 이땐 get말고 is로 받아와야 한다.
		
		// then - 검증
		// 둘이 일치하면 연두색 / 불일치하면 빨간색
		assertEquals(1, value1); 
		assertEquals(true, value2); 
		assertEquals(status, Status.Y); 
		
	}
}
