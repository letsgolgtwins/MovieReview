package com.movie.User;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.User.BO.UserBO;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	// 아이디 중복확인 API
	// http://localhost/user/id-duplicate-check
	@GetMapping("/user/id-duplicate-check")
	public Map<String, Object> IdDuplicateCheck(
			@RequestParam("userId") String userId) {
		// 아이디 중복확인 - db에서 select
		int rowCount = userBO.CheckDuplicateByUserId(userId);
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (rowCount == 1) { // 아이디 중복
			result.put("code", 500);
			result.put("error_message", "아이디 중복");
		} else if (rowCount == 0) { // 아이디 중복 아님. 즉, 아이디 사용 가능
			result.put("code", 200);
			result.put("message", "성공");
		}
		return result;
	}
	
	// 회원가입 API
	// /user/sign-up
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("userId") String userId,
			@RequestParam("userPassword") String userPassword,
			@RequestParam("userName") String userName,
			@RequestParam("userNickName") String userNickName) {
		
		// 회원가입 - db에 insert
		userBO.addUserSignUp(userId, userPassword, userName, userNickName);
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("message", "성공");
		return result;
		
	}
}
