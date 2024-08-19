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
	
	// 이 유저가 이 영화에 대해 이미 쓴 리뷰가 있는 지 확인 - db에서 select
	public int selectReviewCountByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 영화 리뷰 남기기 - db에 insert
	public int insertReviewByMovieIdAndUserOriginId(
			@Param("review") String review, 
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId,
			@Param("userNickName") String userNickName
			);
	
	// 특정 영화의 리뷰들 가져오기 - db에서 여러건 select
	public List<Review> selectMovieReviewListByMovieId(int movieId);
	
	// 특정 영화에 특정 유저가 쓴 리뷰 삭제하기 - db에서 delete
	public int deleteReviewByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			); 
	
	// total 리뷰 개수 - db에서 count select // footer에 총 리뷰개수 나타내기 위해
	public Integer selectTotalReviewCount();
}
