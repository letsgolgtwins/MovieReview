package com.movie.star.mapper;

import java.util.List;
import java.util.Map;

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
	
	// 별점 수정하기 - db에 update
	public int updateStarByMovieIdAndUserOriginIdAndPoint(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId,
			@Param("point") int point
			);
	
	// 특정 영화에 특정 유저가 매긴 별점 삭제 - db에서 delete
	public int deleteStarByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			); 
	
	// 특정 유저가 특정 영화에 '몇 점'을 매겼나 알아내는 메소드 - db에서 int(point) select // 어느순간 이걸 왜 만들었지? 싶은 메소드 
	// > 일단 그 리뷰리스트 화면의 리뷰 article 박스에서 사용자 닉네임 옆에 본인이 매긴 별점 뜨게끔 
	// > 지금 로그인된 유저의 별점만 가져오는 문제
	public Integer selectPointByMovieIdAndUserOriginId(
			@Param("movieId") int movieId, 
			@Param("userOriginId") int userOriginId
			);
	
	// 특정 영화의 특정 유저가 매긴 별점 정보 일단 가져오기 - db에서 select // movieDetail.html에서 star?.point 로 point modal로 보내기 위해
	public Star selectStarInfoByMovieIdAndUserOriginId(
			@Param("movieId") int movieId,
			@Param("userOriginId") int userOriginId
			);
	
	// 특정 영화의 별점 가져오기 - db에서 select // 4.5 위해서
	public Double selectPointByMovieId(int movieId);
	
	// 특정 영화에 몇 명의 유저들이 몇점을 매겼는지 가져오기 - db에서 count select // (1234)위해서
	public Integer selectCountByMovieId(int movieId);
	
	// 0822
	// 특정 유저가 특정 영화에 별점 몇점을 매겼나 - db에서 point select
	public Integer selectPointByMovieIdAndUserId(
			@Param("movieId") int movieId,
			@Param("userId") int userId
			);
	
	// 현재 로그인 된 유저가 매긴 전체 별점 평균 -db에서 select / 0826 
	public Integer selectAvgPointByUser(int userOriginId);
	
	// 현재 로그인 된 유저가 별점을 매긴 영화의 고유idovieId) 목록 - db에서 list select / 0826
	public List<Integer> selectMovieIdListByUserOriginId(int userOriginId);
		
	// 현재 로그인 되어있는 유저가 별점을 매긴 영화의 총 개수 가져오기 - db에서 count select / 내가 남긴 평가 12 이걸 위해서 / 0827
	public int selectTotalStarCountByUserOriginId(int userOriginId);

	// 회원 탈퇴 > 그 유저가 매긴 별점 모두 지우기 - db에서 delete / 0829
	public int deleteAllStar(int userOriginId);
}
