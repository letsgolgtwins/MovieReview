package com.movie.User.BO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserBOTest {

	@Autowired
	UserBO userBO;
	
	@Transactional // 이 어노테이션을 쓰면 실제 insert 과정은 실시되지만 MySQL에 쌓이지 않음. (즉, 테스트는 하되, MySQL데이터베이스에서 귀찮게 지울 필요가 없음)
	@Test
	void 회원가입테스트() {
		userBO.addUserSignUp("아이디", "비밀번호", "이름", "닉네임");
	}
}
