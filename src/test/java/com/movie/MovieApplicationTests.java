package com.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieApplicationTests {

	@Test
	void 더하기테스트() {
		int x = 15;
		int y = 20;
		
		// JUnit 메소드들중 하나
		assertEquals(x + y, 35); // 식, 찐결과값
	}

}
