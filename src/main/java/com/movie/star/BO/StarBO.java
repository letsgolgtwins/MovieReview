package com.movie.star.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.star.mapper.StarMapper;

@Service
public class StarBO {

	// 별점 toggle
	@Autowired
	private StarMapper starMapper;

	// 별점 매기기 toggle
	public void starToggle(int movieId, int userOriginId) { // 누가, 어떤 영화에

		// 이미 별점 매겼나 확인 
		int starTrue = starMapper.selectStarCountByMovieIdAndUserOriginId(movieId, userOriginId);

		if (starTrue == 1) { // 이미 별점 매김
			// 이미 별점이 매겨진 상태니 삭제
			starMapper.deleteStarByMovieIdAndUserOriginId(movieId, userOriginId);
		} else { // 아직 별점 안매긴 상태
			// 별점이 안매겨진 상태이므로 별점을 매김
			starMapper.insertStarByMovieIdAndUserOriginId(movieId, userOriginId);
		}
	}

	// 특정 영화에 특정 유저가 좋아요 눌렀는지 여부 - db에서 count(*) select
	public int getStarCountByMovieIdAndUserOriginId(int movieId, int userOriginId) {
		return starMapper.selectStarCountByMovieIdAndUserOriginId(movieId, userOriginId);
	}
	
	// 별 채울지 여부
	public boolean filledStarByMovieIdAndUserOriginId(int movieId, Integer userOriginId) {
		// 로그인 여부 확인
		if (userOriginId == null) { // 이걸 위해 위에 int가 아닌 Integer로 한 거임
			// 비로그인 상태
			return false;
		} else {
			// 로그인 된 상태
			int starCount = starMapper.selectStarCountByMovieIdAndUserOriginId(movieId, movieId);
			if (starCount == 1) {
				return true;
			} else { // starCount == 0일 경우겠지?
				return false;
			}
		}
	} // 240807 오전 여기까지 했음
	
	
}
