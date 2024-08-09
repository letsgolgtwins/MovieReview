package com.movie.star.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.star.domain.Star;
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
		Integer pastPoint = (Integer) starMapper.selectPointByMovieIdAndUserOriginId(movieId, userOriginId);
		if (count == 0) { // 아직 별점을 안누른 상태
			starMapper.insertStarByMovieIdAndUserOriginIdAndPoint(movieId, userOriginId, point);
		} else { // 이미 별점 누른 상태
			if (point == pastPoint) { // 내가 방금 누른 n번별(point)과 이미 매겨져있는 점수(pastPoint)가 동일한 경우 (즉, 재클릭)
				starMapper.deleteStarByMovieIdAndUserOriginId(movieId, userOriginId); // 재클릭 > 별점 삭제
			} else { // 내가 방금 누른 n번별(point)과 이미 매겨져있는 점수(pastPoint)가 다른 경우 (즉, 점수 수정 = 높이거나 낮추거나)
				starMapper.updateStarByMovieIdAndUserOriginIdAndPoint(movieId, userOriginId, point); // 별점 수정
			}
		}
	}
	
	// 별점 매기기 - db에 insert
	public int addStarByMovieIdAndUserOriginIdAndPoint(int movieId, int userOriginId, int point) {
		return starMapper.insertStarByMovieIdAndUserOriginIdAndPoint(movieId, userOriginId, point);
	}
	
	// 별점 수정하기 - db에 update
	public int updateStarByMovieIdAndUserOriginIdAndPoint(int movieId, int userOriginId, int point) {
		return starMapper.updateStarByMovieIdAndUserOriginIdAndPoint(movieId, userOriginId, point);
	}
	
	// 별점 삭제하기 - db에서 delete
	public int removeStarByMovieIdAndUserOriginId(int movieId, int userOriginId) {
		return starMapper.deleteStarByMovieIdAndUserOriginId(movieId, userOriginId);
	}
	
	// 특정 유저가 특정 영화에 '몇 점'을 매겼나 알아내는 메소드 - db에서 int(point) select
	public int getPointByMovieIdAndUserOriginId(int movieId, int userOriginId) {
		return starMapper.selectPointByMovieIdAndUserOriginId(movieId, userOriginId);
	}
	
	// 특정 영화의 특정 유저가 매긴 별점 정보 일단 가져오기 - db에서 select
	public Star getStarInfoByMovieIdAndUserOriginId(int movieId, int userOriginId) {
		return starMapper.selectStarInfoByMovieIdAndUserOriginId(movieId, userOriginId);
	}
	
	// 특정 영화의 별점 가져오기 - db에서 select
	public int getPointByMovieId(int movieId) {
		return starMapper.selectPointByMovieId(movieId);
	}
}
