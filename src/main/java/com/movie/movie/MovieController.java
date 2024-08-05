package com.movie.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.movie.BO.MovieBO;
import com.movie.movie.domain.Movie;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/movie")
@Controller
public class MovieController {

	@Autowired
	private MovieBO movieBO;
	
	// 홈페이지 - 영화 정보들 뿌리기
	// http://localhost/movie/movie-list-view
	@GetMapping("/movie-list-view")
	public String MoviewListView(Model model, HttpSession session) {
		
		// 로그인 여부 확인
		Integer userOriginId = (Integer) session.getAttribute("userOriginId");
		if (userOriginId == null) { // 비로그인 상태
			// 로그인 페이지로 이동
			return "redirect:/user/sign-in-view";
		}
		
		// 영화 정보들 가져오기
		List<Movie> movie = movieBO.getMovieInfo(); // 여기까지 0802 수업떄 만든 내용
		
		// model에 담기
		model.addAttribute("movieInfo", movie);
		
		return "movie/homePage";
	}
	
	// 영화 상세 화면
	// http://localhost/movie/movie-detail-view
	@GetMapping("/movie-detail-view")
	public String MovieDetailView(@RequestParam("movieId") int movieId, Model model) {
	
		// Movie에서 단 건 조회
		Movie movie = movieBO.getMovieByUserOriginIdAndMovieId(movieId);
		
		// model에 담기
		model.addAttribute("movieInfo", movie);
		
		// 상세 페이지로 이동
		return "movie/movieDatailView";
	}
}
