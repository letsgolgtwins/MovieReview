package com.movie.User.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.movie.User.domain.User;

@Mapper
public interface UserMapper {

	// 아이디 중복확인 - db에서 select
	public int selectCountByUserId(String userId);
	
	// 회원가입 - db에 insert
	public int insertUserSignUp(
			@Param("userId") String userId, 
			@Param("hashedUserPassword") String hashedUserPassword, 
			@Param("userName") String userName, 
			@Param("userNickName") String userNickName
			);
	
	// 로그인 - db에서 select
	public User selectUserByUserIdAndUserPassword(
			@Param("userId") String userId, 
			@Param("userPassword") String userPassword
			);
	
	//////////////////////////////////////////////////////////////////////////////////
	
	// 뉴 아이디 중복확인 - db에서 select
	public int selectDuplicateCount(String newUserId);

	// 아이디 변경 - db에서 update / 0828
	public int updateId(
			@Param("newUserId") String newUserId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 비밀번호 변경 - db에서 update / 0828
	public int updatePassword(
			@Param("hashedNewUserPassword") String hashedNewUserPassword, 
			@Param("userOriginId") int userOriginId
			);

	// 닉네임 변경 - db에서 update / 0828
	public int updateNickName(
			@Param("newUserNickName") String newUserNickName, 
			@Param("userOriginId") int userOriginId
			);
}
