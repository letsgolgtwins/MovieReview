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
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// 뉴 아이디 중복확인 - db에서 select
	public int checkDuplicateNewUserId(String newUserId) {
		return userMapper.selectDuplicateCount(newUserId);
	}
	
	// 아이디 변경 - db에서 update / 0828
	public int updateId(String newUserId, int userOriginId) {
		return userMapper.updateId(newUserId, userOriginId);
	}
	
	// 기존 비밀번호가 일치하는가 - db에서 select / 0829 추가
	public int correctCheckUserPassword(String hashedOriginPassword, int userOriginId) {
		return userMapper.correctCheckUserPassword(hashedOriginPassword, userOriginId);
	}
	
	// 비밀번호 변경 - db에서 update / 0828
	public int updatePassword(String hashedNewUserPassword, int userOriginId) {
		return userMapper.updatePassword(hashedNewUserPassword, userOriginId);
	}
	
	// 닉네임 변경 - db에서 update / 0828
	public int updateNickName(String newUserNickName, int userOriginId) {
		return userMapper.updateNickName(newUserNickName, userOriginId);
	}
	
	// 회원 탈퇴 - db에서 delete / 0829
	public int deleteUser(int userOriginId) {
		return userMapper.deleteUser(userOriginId);
	}
}
