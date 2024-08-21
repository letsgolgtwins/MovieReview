package com.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

//0821
public class EnumTest2 {
	
	public enum CalcType {
		// 열거형 정의
		CALC_A(value -> value), // 람다식. value가 들어오면 그냥 value로 리턴한다.
		CALC_B(value -> value * 10),
		CALC_C(value -> value * 3),
		CALC_ETC(value -> 0);
		
		// 필드 정의 -> enum에 정의된 function
		private Function<Integer, Integer> expression; // 임포트 > java.util.function
		
		// 생성자
		CalcType(Function<Integer, Integer> expression) {
			this.expression = expression;
		}
		
		// 계산 적용 메소드
		public int calculate(int value) {
			return expression.apply(value);
		}
	}
	
	@Test
	void 계산태스트() {
		// given
		CalcType ctype = CalcType.CALC_C;
		
		// when
		int result = ctype.calculate(500);
		
		// then
		assertEquals(1500, result); // 왼쪽은 이미 알고있는 결과와 result 의 비교 > JUnit Test
	}
}
