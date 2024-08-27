package com.movie.ReviewList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.Review.BO.ReviewBO;
import com.movie.ReviewList.BO.ReviewListBO;
import com.movie.ReviewList.domain.ReviewCard;
import com.movie.ReviewList.domain.ReviewCardByUser;
import com.movie.star.BO.StarBO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@RequestMapping("/reviewList")
@Controller
public class ReviewListController {
	
	@Autowired
	private ReviewBO reviewBO;
	
	@Autowired
	private StarBO starBO;
	
	@Autowired
	private ReviewListBO reviewListBO;
	
	// 특정 영화의 리뷰 리스트 페이지
	// /reviewList/reviewList-view
	// /reviewList/reviewList-view?movieId=
	@GetMapping("/reviewList/reviewList-view")
	public String ReviewListView(
			@RequestParam("movieId") int movieId, Model model) {
		
		// 큰 틀
		List<ReviewCard> reviewCardList = reviewListBO.generateReviewCardList(movieId);
		
		// model에 담기
		model.addAttribute("reviewCard", reviewCardList);
		
		return "review/movieReviewList";
	}
	
	// 특정 유저가 남긴 리뷰 코멘트들 리스트 페이지(0827)
	@GetMapping("/review/my-review-view")
	public String MyReviewView(HttpSession session, Model model) {
		// session에서 userOriginId 가져오기
		int userOriginId = (int) session.getAttribute("userOriginId");
		
		// 큰 틀
		List<ReviewCardByUser> reviewCardList2 = reviewListBO.generateReviewCardListByUser(userOriginId);
		
		// model에 담기
		model.addAttribute("reviewCard2", reviewCardList2);
		
		// view로 이동
		return "review/myReviewList";
	}
}
