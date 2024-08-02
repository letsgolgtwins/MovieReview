package com.movie.movie.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movie.domain.Movie;
import com.movie.movie.mapper.MovieMapper;

@Service
public class MovieBO {

	@Autowired
	private MovieMapper movieMapper;
	
	// 영화 정보들 가져오기 - db에서 select
	public Movie getMovieInfo() {
		return movieMapper.selectMovieInfo();
	}
}
