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
import com.movie.star.BO.StarBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/review")
@Controller
public class ReviewController {

	@Autowired
	private ReviewBO reviewBO;

	@Autowired
	private StarBO starBO; // 240812 오후 추가 (리뷰리스트 화면에 닉네임 옆에 본인들이 매긴 별점 뜨게끔 하기 위해)
	
	// 특정 영화의 리뷰들 목록 페이지
	// http://localhost/review/movie-review-list?movieId=
	@GetMapping("/movie-review-list")
	public String MovieReviewList(
			@RequestParam("movieId") int movieId, Model model, HttpSession session) { // httpsession으로 userOriginId를 가져오려고 했으나, 그건 지금 로그인된 유저의 userOriginId를 가져어는 것이므로 그렇게하면 안된다.
		
		// session에서 userOriginId 가져오기
		Integer userOriginId = (Integer) session.getAttribute("userOriginId");
		
		// 특정 영화의 리뷰들 가져오기 - db에서 여러건 select > 카드뷰처럼 만들기 재시도
		List<Review> reviewList = reviewBO.getMovieReviewListByMovieId(movieId);
		
		// 특정 영화의 현재 로그인 된 유저가 몇점을 매겼나 - db에서 point select // 0812 추가 > 지금 로그인된 유저의 별점만 가져오는 문제
		Integer point = (Integer) starBO.getPointByMovieIdAndUserOriginId(movieId, userOriginId);
		if (point == null) { // 별점은 안매기고 리뷰만 쓴 거임.
			point = 0;
		} else {
			point = (Integer) starBO.getPointByMovieIdAndUserOriginId(movieId, userOriginId);
		}

		// model에 담기
		model.addAttribute("reviewInfo", reviewList); // 리뷰들과 그 리뷰들을 쓴 닉네임을 나타내기 위해
		model.addAttribute("point", point); // 현재 로그인 되어있는 유저가 몇점을 줬나 나타내기 위해 / 0812 추가 > 지금 로그인된 유저의 별점만 가져오는 문제
		
		return "review/movieReviewList";
	}
}
