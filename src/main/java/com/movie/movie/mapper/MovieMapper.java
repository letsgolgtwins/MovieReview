package com.movie.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.movie.movie.domain.Movie;

@Mapper
public interface MovieMapper {

	// 영화 정보 가져오기 - db에서 select
	public List<Movie> selectMovieInfo();
}
