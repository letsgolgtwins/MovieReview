package com.movie.movie.BO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movie.domain.Movie;
import com.movie.movie.mapper.MovieMapper;
import com.movie.star.BO.StarBO;

@Service
public class MovieBO {

	@Autowired
	private MovieMapper movieMapper;
	
	// 영화 목록들 가져오기 - db에서 select
	public List<Movie> getMovieInfo() {
		return movieMapper.selectMovieInfo();
	}
	
	// 특정 영화 상세 정보 가져오기 (단 건) - db에서 select
	public Movie getMovieByMovieId(int movieId) {
		return movieMapper.selectMovieByMovieId(movieId);
	}
	
	// 영화 정보 검색 - db에서 select
	public Integer getMovieIdBySearch(String movieInfo) {
		return movieMapper.selectMovieIdBySearch(movieInfo);
	}
	
	// list에서 뽑아온 movieId로 영화 정보 가져오기 - db에서 select / 0826
	public List<Movie> getMovieInfoByMovieId(List<Integer> movieIdList) {
		
		List<Movie> movieInfoList = new ArrayList<>(); // 리스트 만들기
		
		for (Integer movieId : movieIdList) { // movieIdList를 반복문 돌려서 movieId를 하나씩 꺼내옴.
			Movie movieInfo = movieMapper.selectMovieInfoByMovieId(movieId); // 꺼내온 movieId로 movie의 정보들 가져와서 movieInfo에다 저장
			if (movieInfo != null) {
				movieInfoList.add(movieInfo);
			}
		}
		
		return movieInfoList;
	}
	
	// 현재 로그인 되어있는 유저가 리뷰를 남긴 영화들 가져오기 - db에서 select / 0827
	public List<String> getMovieNameByMovieId(int movieId) {
		return movieMapper.selectMovieNameByMovieId(movieId);
	}
}
