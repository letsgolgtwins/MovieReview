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
	
	// 특정 영화의 특정 유저가 매긴 별점 정보 일단 가져오기 - db에서 select
	public List<Star> getStarInfoByMovieIdAndUserOriginId(int movieId, int userOriginId) {
		return starMapper.selectStarInfoByMovieIdAndUserOriginId(movieId, userOriginId);
	}
	
}
