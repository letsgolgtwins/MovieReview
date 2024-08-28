package com.movie.User.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.User.domain.User;
import com.movie.User.mapper.UserMapper;

@Service
public class UserBO {

	@Autowired
	private UserMapper userMapper;
	
	// 아이디 중복확인 - db에서 select
	public int CheckDuplicateByUserId(String userId) {
		return userMapper.selectCountByUserId(userId);
	}
	
	// 회원가입 - db에 insert
	public void addUserSignUp(String userId, String hashedUserPassword, String userName, String userNickName) {
		userMapper.insertUserSignUp(userId, hashedUserPassword, userName, userNickName);
	}
	
	// 로그인 - db에서 select
	public User getUserByUserIdAndUserPassword(String userId, String userPassword) {
		return userMapper.selectUserByUserIdAndUserPassword(userId, userPassword);
	}
	
	// 아이디 변경 - db에서 update / 0828
	public int updateId(String newUserId, int userOriginId) {
		return userMapper.updateId(newUserId, userOriginId);
	}
}
