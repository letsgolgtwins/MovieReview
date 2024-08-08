package com.movie.star.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.star.mapper.StarMapper;

@Service
public class StarBO {

	// 별점 toggle
	@Autowired
	private StarMapper starMapper;

	// 특정 영화에 특정 유저가 좋아요 눌렀는지 여부 - db에서 count(*) select
	public int getStarCountByMovieIdAndUserOriginId(int movieId, int userOriginId) {
		return starMapper.selectStarCountByMovieIdAndUserOriginId(movieId, userOriginId);
	}

	// 별점 toggle 
	public void starToggle(int movieId, int userOriginId, int point) {
		int count = starMapper.selectStarCountByMovieIdAndUserOriginId(movieId, userOriginId);
		if (count == 0) { // 아직 별점을 안누른 상태 > 클릭 > 별점 매김
			starMapper.insertStarByMovieIdAndUserOriginIdAndPoint(movieId, userOriginId, point);
		} else { // 이미 별점 누른 상태 > 재클릭 > 별점 삭제 
			starMapper.deleteStarByMovieIdAndUserOriginId(movieId, userOriginId);
		}
	}
	
	// 별점 매기기 - db에 insert
	public int addStarByMovieIdAndUserOriginIdAndPoint(int movieId, int userOriginId, int point) {
		return starMapper.insertStarByMovieIdAndUserOriginIdAndPoint(movieId, userOriginId, point);
	}
	
	// 별점 삭제하기 - db에서 delete
	public int removeStarByMovieIdAndUserOriginId(int movieId, int userOriginId) {
		return starMapper.deleteStarByMovieIdAndUserOriginId(movieId, userOriginId);
	}
	
	// 별 채울지 여부
//	public int filledStarByMovieIdAndUserOriginId(int movieId, int userOriginId) {
//		// 로그인 여부 확인 절차 - 생략
//		
//		int starCount = starMapper.selectStarCountByMovieIdAndUserOriginId(movieId, userOriginId);
//		if (starCount == 1) { // 1번 별 누름
//			return 1;
//		} else if (starCount == 2) { // 2번 별 누름
//			return 2;
//		} else if (starCount == 3) { // 3번 별 누름
//			return 3;
//		} else if (starCount == 4) { // 4번 별 누름
//			return 4; 
//		} else { // 5번 별 누름
//			return 5;
//		}
//	
//	} 
	
}
