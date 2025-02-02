package com.movie.movie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.Review.BO.ReviewBO;
import com.movie.aop.TimeTrace;
import com.movie.movie.BO.MovieBO;
import com.movie.movie.domain.Movie;
import com.movie.star.BO.StarBO;
import com.movie.star.domain.Star;

import groovyjarjarantlr4.v4.parse.GrammarTreeVisitor.mode_return;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/movie")
@Controller
public class MovieController {

	@Autowired
	private MovieBO movieBO;
	
	// 0809오전 추가
	@Autowired
	private StarBO starBO;
	
	// 0821오후 추가
	@Autowired
	private ReviewBO reviewBO;
	
	// 홈페이지 - 영화 정보들 뿌리기
	// http://localhost/movie/movie-list-view
	@TimeTrace // AOP 추가 (0814 오후)
	@GetMapping("/movie-list-view")
	public String MoviewListView(Model model, HttpSession session) {
		
		// 로그인 여부 확인
//		Integer userOriginId = (Integer) session.getAttribute("userOriginId");
//		if (userOriginId == null) { // 비로그인 상태
//			// 로그인 페이지로 이동
//			return "redirect:/user/sign-in-view";
//		}
		
		// 영화 정보들 가져오기
		List<Movie> movie = movieBO.getMovieInfo(); // 여기까지 0802 수업떄 만든 내용
		
		// model에 담기
		model.addAttribute("movieInfo", movie);
		
		return "movie/homePage";
	}
	
	// 영화 상세 화면
	// http://localhost/movie/movie-detail-view
	@GetMapping("/movie-detail-view")
	public String MovieDetailView(@RequestParam("movieId") int movieId, Model model, HttpSession session) {
		
		// 0809 오전 추가 -파라미터로 movieId(이미 리퀘스트 파람으로 되어있음), userOriginId(session으로)를 받아온다
		Integer userOriginId = (Integer) session.getAttribute("userOriginId");
		
		// Movie에서 단 건 조회
		Movie movie = movieBO.getMovieByMovieId(movieId);
		
		// 0809 오전 추가 - Star 가져오기 
		Star star = starBO.getStarInfoByMovieIdAndUserOriginId(movieId, userOriginId);
		
		// 0809 오후 추가 - 영화별 평점 가져오기
		Double AvgPoint = (Double) starBO.getPointByMovieId(movieId);

		// 0812 오후 추가 - 영화별 몇명이나 별점 매겼나 가져오기
		Integer starCount = (Integer) starBO.getCountByMovieId(movieId);
		
		// 0812 수정 - 영화 평균 별점
		if (AvgPoint == null) {
			AvgPoint = 0.0; // 아직 아무도 별점을 매기지 않았다면 0.0
		} else {
			AvgPoint = starBO.getPointByMovieId(movieId);
		}
		
		// 0829 오후 추가 - 영화별 평점인데 소수점 둘째자리에서 반올림한 결과로 나타내기
		Double roundedAvgPoint = (double) Math.round(AvgPoint * 10) / 10; 

		// 0812 오후 추가 - 별점을 몇 명이나 매겼나
		if (starCount == null) {
			starCount = 0;
		} else {
			starCount = (Integer) starBO.getCountByMovieId(movieId);
		}
		
		// model에 담기
		model.addAttribute("movieInfo", movie);
		model.addAttribute("starInfo", star); // 0809 오전 추가
		//model.addAttribute("pointAvg", AvgPoint); // 0809 오후 추가
		model.addAttribute("pointAvg", roundedAvgPoint); // 0829 오후 추가 > 이걸로 변경 (3.2777점 보단 3.3 점이 나으니까)
		model.addAttribute("starCount", starCount); // 0812 오후 추가
		
		// 상세 페이지로 이동
		return "movie/movieDetailView";
	}
	
	// 0826  
	// 현재 로그인 되어있는 유저가 매긴 별점이 있는 영화 목록들 화면
	@GetMapping("/my-movie-view")
	public String myMovieView(HttpSession session, Model model) {
		// session에서 userOriginId 가져오기
		int userOriginId = (int) session.getAttribute("userOriginId");

		// 현재 로그인된 유저가 별점을 매긴 영화의 고유 id(movieId) 목록들을 starBO로 부터 가져오기
		List<Integer> movieIdList = starBO.getMovieIdListByUserOriginId(userOriginId);
		
		// movie테이블에서 movie정보들 가져오기
		List<Movie> movieInfoList = movieBO.getMovieInfoByMovieId(movieIdList);
		
		// model에 담기
		model.addAttribute("movieInfos", movieInfoList);

		// view화면으로 이동
		return "movie/myMovie";
	}
}