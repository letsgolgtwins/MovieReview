package com.movie.star.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StarMapper {

	// 특정 영화에 특정 유저가 좋아요 눌렀는지 여부 - db에서 count(*) select (int 1 아니면 0으로 나올 예정)
	public int selectStarCountByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 별점 매기는 toggle
	public void insertStarByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);

	// 별점 지우는 toggle
	public void deleteStarByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
}
