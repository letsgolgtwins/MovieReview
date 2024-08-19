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
import com.movie.ReviewList.domain.ReviewAndStar;
import com.movie.star.BO.StarBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/reviewList")
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
	@GetMapping("/reviewList-view")
	public String ReviewListView(
			@RequestParam("movieId") int movieId, Model model, @RequestParam("userOriginId") int userOriginId) {
		
		List<ReviewAndStar> reviewAndStarList = reviewListBO.generateReviewList(movieId);
	
		// model에 담기
		model.addAttribute("reviewAndStarInfo", reviewAndStarList);
		
		return "review/movieReviewList";
	}
}
