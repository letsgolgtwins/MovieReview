package com.movie.Review;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.Review.BO.ReviewBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/review")
@RestController
public class ReviewRestController {

	@Autowired
	private ReviewBO reviewBO;
	
	// 영화 리뷰 남기는 API
	// /review/create-review
	@PostMapping("/create-review")
	public Map<String, Object> CreateReview(
			@RequestParam(value = "review", required = false) String review,
			@RequestParam("movieId") int movieId,
			HttpSession session) {
		
		// session에서 userOriginId, userNickName 가져오기
		int userOriginId = (int) session.getAttribute("userOriginId");
		String userNickName = (String) session.getAttribute("userNickName");
		
		// 리뷰 남기기 - db에 insert / 파라미터는 위의 세 개
		int rowCount = reviewBO.addReviewByMovieIdAndUserOriginId(review, movieId, userOriginId, userNickName);
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (rowCount == 1) { // 리뷰 남기기 성공
			result.put("code", 200);
			result.put("message", "성공");
		} else { // 리뷰 남기기 실패
			result.put("code", 500);
			result.put("error_message", "리뷰 남기기 실패");
		}
		return result;
	}
}
