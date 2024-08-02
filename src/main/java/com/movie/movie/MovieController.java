package com.movie.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.movie.movie.BO.MovieBO;
import com.movie.movie.domain.Movie;

@RequestMapping("/movie")
@Controller
public class MovieController {

	@Autowired
	private MovieBO movieBO;
	
	// 홈페이지
	// http://localhost/movie/movie-list-view
	@GetMapping("/movie-list-view")
	public String MoviewListView(Model model) { // 근데 이 model 임포트 저거 제대로 된 게 맞는지 확인!import org.springframework.ui.Model; 
		// 영화 정보들 가져오기
		Movie movie = movieBO.getMovieInfo(); // 여기까지 0802 수업떄 만든 내용
		
		// model에 담기
		
		return "movie/homePage";
	}
	
	// 
}
