package com.movie.User.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

	// 아이디 중복확인 - db에서 select
	public int selectCountByUserId(String userId);
	
	// 회원가입 - db에 insert
	public int insertUserSignUp(
			@Param("userId") String userId, 
			@Param("userPassword") String userPassword, 
			@Param("userName") String userName, 
			@Param("userNickName") String userNickName
			);
}
