package com.movie.Review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.Review.BO.ReviewBO;
import com.movie.Review.domain.Review;

@RequestMapping("/review")
@Controller
public class ReviewController {

	@Autowired
	private ReviewBO reviewBO;

	// 특정 영화의 리뷰들 목록 페이지
	// http://localhost/review/movie-review-list?movieId=

	@GetMapping("/movie-review-list")
	public String MovieReviewList(@RequestParam("movieId") int movieId, Model model) {
		
		// 특정 영화의 리뷰들 가져오기 - db에서 여러건 select 
		List<Review> reviewList = reviewBO.getMovieReviewListByMovieId(movieId);

		// model에 담기
		model.addAttribute("reviewInfo", reviewList);
		
		return "review/movieReviewList";
	}

	// 240811 modal 만들기
}
