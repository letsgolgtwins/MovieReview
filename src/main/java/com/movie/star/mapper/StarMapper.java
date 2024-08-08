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
	
	// 1번 별 클릭 > 별점 1점 매기는 toggle
	public void insertStar1ByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);

	// 1번 별 재클릭 > 별점 1점 지우는 toggle
	public void deleteStar1ByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 2번 별 클릭 > 별점 2점 매기는 toggle
	public void insertStar2ByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 2번 별 재클릭 > 별점 2점 지우는 toggle
	public void deleteStar2ByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 3번 별 클릭 > 별점 3점 매기는 toggle
	public void insertStar3ByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 3번 별 재클릭 > 별점 3점 지우는 toggle
	public void deleteStar3ByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 4번 별 클릭 > 별점 4점 매기는 toggle
	public void insertStar4ByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 4번 별 재클릭 > 별점 4점 지우는 toggle
	public void deleteStar4ByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 5번 별 클릭 > 별점 5점 매기는 toggle
	public void insertStar5ByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 5번 별 재클릭 > 별점 5점 지우는 toggle
	public void deleteStar5ByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
}
