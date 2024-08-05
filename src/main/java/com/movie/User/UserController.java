package com.movie.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
