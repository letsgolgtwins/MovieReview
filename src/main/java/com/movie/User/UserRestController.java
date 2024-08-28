package com.movie.User;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.Review.BO.ReviewBO;
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
	
	@Autowired
	private ReviewBO reviewBO;
	
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// 아이디 변경 중복확인 API
	// /user/check-duplicate-new-id
	@PostMapping("/check-duplicate-new-id")
	public Map<String, Object> checkDuplicateNewId(
			@RequestParam("newUserId") String newUserId) {
		// 뉴아이디 중복확인 - db에서 select 
		int duplicate = userBO.checkDuplicateNewUserId(newUserId);
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (duplicate == 1) { // 중복이라는 소리
			result.put("code", 500);
			result.put("message", "중복입니다.");
		} else { // 중복이 아니라는 소리
			result.put("code", 200);
			result.put("message", "중복이 아닙니다.");
		}
		return result;
	}
	
	// 아이디 변경 API
	// /user/id-update
	@PostMapping("/id-update")
	public Map<String, Object> IdUpdate(
			@RequestParam("newUserId") String newUserId, HttpSession session) {
		
		// 현재 로그인 되어있는 유저의 고유 pk id 가져오기 - userOriginId 
		int userOriginId = (int) session.getAttribute("userOriginId");
		
		// 아이디 변경 - db에서 update
		int updateId = userBO.updateId(newUserId, userOriginId);
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (updateId == 1) { // 아이디 변경 성공
			result.put("code", 200);
			result.put("message", "아이디 변경 성공");
		} else { // 에러
			result.put("code", 500);
			result.put("error_message", "아이디 변경 실패");
		}
		return result;
	}
	
	// 비밀번호 변경 API
	// /user/password-update
	@PostMapping("/password-update")
	public Map<String, Object> PasswordUpdate(
			@RequestParam("newUserPassword") String newUserPassword, HttpSession session) {
		
		// session에서 userOriginId 꺼내오기
		int userOriginId = (int) session.getAttribute("userOriginId");
		
		// 뉴 비밀번호 암호화하기
		String hashedNewUserPassword = EncryptUtils.md5(newUserPassword);
		
		// 비밀번호 변경 - db에서 update
		int updatePassword = userBO.updatePassword(hashedNewUserPassword, userOriginId);
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (updatePassword == 1) { // 비밀번호 변경 성공
			result.put("code", 200);
			result.put("message", "비밀번호 변경 성공.");
		} else {
			result.put("code", 500);
			result.put("error_message", "비밀번호 변경 실패");
		}
		return result;
		
	}
	
	// 닉네임 변경 API
	// /user/nickName-update
	@PostMapping("/nickName-update")
	public Map<String, Object> NickNameUpdate(
			@RequestParam("newUserNickName") String newUserNickName, HttpSession session) {
		// 현재 로그인 되어있는 유저의 고유 id pk 추출
		int userOriginId = (int) session.getAttribute("userOriginId");
		
		// 닉네임 변경 - user와 review에서 모두
		int updateNickName = userBO.updateNickName(newUserNickName, userOriginId); // user에서 변경
		reviewBO.updateNickName(newUserNickName, userOriginId); // review에서 변경
	
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (updateNickName == 1) { // 닉네임 변경 성공
			result.put("code", 200);
			result.put("message", "닉네임 변경 성공.");
		} else { // 닉네임 변경 실패
			result.put("code", 500);
			result.put("error_message", "닉네임 변경 실패.");
		}
		return result;
	}
	
}
