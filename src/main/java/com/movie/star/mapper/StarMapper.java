package com.movie.star.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.movie.star.domain.Star;

@Mapper
public interface StarMapper {

	// 특정 영화에 특정 유저가 좋아요 눌렀는지 여부 - db에서 count(*) select (int 1 아니면 0으로 나올 예정)
	public int selectStarCountByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 특정 영화에 특정 유저가 별점 매기기 - db에 insert
	public int insertStarByMovieIdAndUserOriginIdAndPoint(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId,
			@Param("point") int point
			); 
	
	// 특정 영화에 특정 유저가 매긴 별점 삭제 - db에서 delete
	public int deleteStarByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			); 
	
	// 특정 영화의 특정 유저가 매긴 별점 정보 일단 가져오기 - db에서 select 
	public List<Star> selectStarInfoByMovieIdAndUserOriginId(
			@Param("movieId") int movieId,
			@Param("userOriginId") int userOriginId
			);
		
}
