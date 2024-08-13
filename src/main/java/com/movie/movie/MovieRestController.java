package com.movie.movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movie.BO.MovieBO;
import com.movie.movie.domain.Movie;

@RequestMapping("/movie")
@RestController // 0813 오후에 만들었음
public class MovieRestController {

	@Autowired
	private MovieBO movieBO;
	
	// 영화, 연도, 장르, 국가, 감독 검색 기능 API
	// /movie/movie-search
	@GetMapping("/movie-search")
	public Map<String, Object> MovieSearch(
			@RequestParam("movieName") String movieName,
			@RequestParam("movieYear") int movieYear,
			@RequestParam("movieGenre") String movieGenre,
			@RequestParam("movieNation") String movieNation,
			@RequestParam("movieDirector") String movieDirector) {
		
		// 영화 정보 검색해서 영화 객체 받기
		List<Movie> movieId = movieBO.getMovieIdByMovieNameMovieYearMovieGenreMovieNationMovieDirector(movieName, movieYear, movieGenre, movieNation, movieDirector);
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (movieId == null) { // 해당하는 영화 없음
			result.put("code", 500);
			result.put("error_message", "해당하는 영화가 없습니다.");
		} else { // 
			result.put("code", 200);
			result.put("message", "성공");
			result.put("movieId", movieId);
		}
		return result;
	}
}
