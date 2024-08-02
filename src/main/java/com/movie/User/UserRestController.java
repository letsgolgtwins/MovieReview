package com.movie.User;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.User.BO.UserBO;
import com.movie.User.domain.User;
import com.movie.common.EncryptUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	// 아이디 중복확인 API
	// http://localhost/user/id-duplicate-check
	@PostMapping("/id-duplicate-check")
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
	public Map<String, Object> SignUp(
			@RequestParam("userId") String userId,
			@RequestParam("userPassword") String userPassword,
			@RequestParam("userName") String userName,
			@RequestParam("userNickName") String userNickName) {
		
		// userPassword 암호화
		String hashedUserPassword = EncryptUtils.md5(userPassword);
		
		// 회원가입 - db에 insert
		userBO.addUserSignUp(userId, hashedUserPassword, userName, userNickName);
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("message", "성공");
		return result;
	}
	
	// 로그인 API
	// /user/sign-in
	@PostMapping("/sign-in")
	public Map<String, Object> SignIn(
			@RequestParam("userId") String userId,
			@RequestParam("userPassword") String userPassword,
			HttpServletRequest request) {
		
		// hashing 처리
		String hashedPassword = EncryptUtils.md5(userPassword);
		
		// 로그인 - db에서 조회 (후에 session에 정보를 저장해야 하므로 User 도메인으로 가져온다)
		User user = userBO.getUserByUserIdAndUserPassword(userId, hashedPassword);
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (user != null) { // 즉, 가입된 유저가 있어서 로그인 성공일 때
			HttpSession session = request.getSession();
			session.setAttribute("userOriginId", user.getId());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userNickName", user.getUserNickName());
			
			result.put("code", 200);
			result.put("message", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "로그인 실패");
		}
		return result;
	}
}
