package com.movie.Review.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.movie.Review.domain.Review;

@Mapper
public interface ReviewMapper {

	// test4 용
	public List<Map<String, Object>> selectReviewListTest();
	
	// 영화 리뷰 남기기 - db에 insert
	public int insertReviewByMovieIdAndUserOriginId(
			@Param("review") String review, 
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId,
			@Param("userNickName") String userNickName
			);
	
	// 특정 영화의 리뷰들 가져오기 - db에서 여러건 select
	public List<Review> selectMovieReviewListByMovieId(int movieId);
}
