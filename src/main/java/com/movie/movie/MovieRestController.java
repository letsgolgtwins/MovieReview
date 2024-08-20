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
	
	// 영화 검색 기능 API
	// /movie/movie-search
	@GetMapping("/movie-search")
	public Map<String, Object> MovieSearch(@RequestParam("movieInfo") String movieInfo) {
		
		// 영화 정보 검색해서 영화 객체 받기
		Integer movieId = (Integer) movieBO.getMovieIdBySearch(movieInfo);
		
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
