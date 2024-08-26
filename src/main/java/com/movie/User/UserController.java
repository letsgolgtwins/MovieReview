package com.movie.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.movie.star.BO.StarBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {

	// 회원가입 페이지로 이동
	// http://localhost/user/sign-up-view
	@GetMapping("/sign-up-view")
	public String SignUpView() {
		return "user/SignUp";
	}
	
	// 로그인 페이지로 이동
	// http://localhost/user/sign-in-view
	@GetMapping("/sign-in-view")
	public String SignInView() {
		return "user/SignIn";
	}
	
	// 로그아웃
	// /user/sign-out
	@GetMapping("/sign-out")
	public String SignOut(HttpSession session) {
		session.removeAttribute("userOriginId");
		session.removeAttribute("userId");
		return "redirect:/user/sign-in-view";
	}
	
	// 마이페이지로 이동 (0821)
	@Autowired
	private StarBO starBO;
	// http://localhost/user/mypage-view
	@GetMapping("/mypage-view")
	public String MyPage(Model model, HttpSession session) {
		// userOriginId 가져오기
		Integer userOriginId = (Integer) session.getAttribute("userOriginId");
		
		// 현재 로그인 되어있는 유저의 전체 별점 평균 가져오기
		Double avgPoint = (double) starBO.getAvgPointByUser(userOriginId);
		
		// model에 담기
		model.addAttribute("avgPoint", avgPoint);
		
		// view 화면
		return "user/MyPage";
	}
	
	// 내 정보 수정화면으로 이동(0826)
	@GetMapping("/modify-my-profile")
	public String ModifyMyProfile() {
		return "user/MyProfile";
	}
}
