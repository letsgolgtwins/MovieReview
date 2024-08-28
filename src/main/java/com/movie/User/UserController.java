package com.movie.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.movie.Review.BO.ReviewBO;
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
	
	@Autowired
	private ReviewBO reviewBO;
	
	// http://localhost/user/mypage-view
	@GetMapping("/mypage-view")
	public String MyPage(Model model, HttpSession session) {
		// userOriginId 가져오기
		Integer userOriginId = (Integer) session.getAttribute("userOriginId");
		
		// 현재 로그인 되어있는 유저의 전체 별점 평균 가져오기 / 0826
		Double avgPoint = (double) starBO.getAvgPointByUser(userOriginId);
		
		// 현재 로그인 되어있는 유저가 별점을 매긴 영화의 총 개수 가져오기 / 0827
		int totalStarCount = starBO.getTotalStarCountByUserOriginId(userOriginId);
		
		// 현재 로그인 되어있는 유저가 리뷰를 남긴 영화의 총 개수 가져오기
		int totalReviewCountByUserOriginId = reviewBO.getTotalReviewCountByUserOriginId(userOriginId);
		
		// model에 담기
		model.addAttribute("avgPoint", avgPoint); // 0826
		model.addAttribute("totalStarCount", totalStarCount); // 0827
		model.addAttribute("totalReviewCountByUserOriginId", totalReviewCountByUserOriginId); // 0827
		
		// view 화면
		return "user/MyPage";
	}
	
	// 내 정보 수정화면으로 이동(0826)
	@GetMapping("/modify-my-profile")
	public String ModifyMyProfile() {
		return "user/MyProfile";
	}
}
