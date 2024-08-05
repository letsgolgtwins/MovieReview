package com.movie.movie.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movie.domain.Movie;
import com.movie.movie.mapper.MovieMapper;

@Service
public class MovieBO {

	@Autowired
	private MovieMapper movieMapper;
	
	// 영화 목록들 가져오기 - db에서 select
	public List<Movie> getMovieInfo() {
		return movieMapper.selectMovieInfo();
	}
	
	// 특정 영화 상세 정보 가져오기 (단 건) - db에서 select
	public Movie getMovieByUserOriginIdAndMovieId(int movieId) {
		return movieMapper.selectMovieByUserOriginIdAndMovieId(movieId);
	}
}
