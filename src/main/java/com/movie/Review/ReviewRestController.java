package com.movie.Review;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		
		// 리뷰 남기기 - db에 insert / 파라미터는 위의 4개
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
	
	// 본인이 쓴 영화 리뷰 삭제 API
	// /review/delete-review
	@DeleteMapping("/delete-review")
	public Map<String, Object> DeleteReview(
			@RequestParam("movieId") int movieId, HttpSession session) {
		// session에서 userOriginId 가져오기
		int userOriginId = (int) session.getAttribute("userOriginId");
		
		// 본인이 쓴 리뷰 삭제하기
		int rowCount = reviewBO.deleteReviewByMovieIdAndUserOriginId(movieId, userOriginId);
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (rowCount == 1) { // 삭제 성공
			result.put("code", 200);
			result.put("message", "삭제 성공");
		} else { // 삭제 실패(rowCount == 0)
			result.put("code", 500);
			result.put("error_message", "삭제 실패");
		}
		return result;
		
	}
}
