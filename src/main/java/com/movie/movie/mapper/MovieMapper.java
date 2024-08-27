package com.movie.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.movie.movie.domain.Movie;

@Mapper
public interface MovieMapper {

	// 영화 목록들 가져오기 - db에서 select
	public List<Movie> selectMovieInfo();
	
	// 특정 영화 상세 정보 가져오기 (단 건) - db에서 select
	public Movie selectMovieByMovieId(int movieId);
	
	// 영화 정보 검색 - db에서 select
	public Integer selectMovieIdBySearch(String movieInfo);
	
	// list에서 뽑아온 movieId로 영화 정보 가져오기 - db에서 select / 0826
	public Movie selectMovieInfoByMovieId(int movieId);
	
	// 현재 로그인 된 유저가 리뷰를 남긴 영화들 영화 제목들 가져오기 - db에서 select / 0827
	public List<String> selectMovieNameByMovieId(int movieId);
}
