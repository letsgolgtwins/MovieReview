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

	// 1번별에 대한 toggle - 클릭시 1, 재클릭시 0
	public void StarToggle1(int movieId, int userOriginId) { 
		// 이미 별점 매겼나 확인
		int count1 = starMapper.selectStarCountByMovieIdAndUserOriginId(movieId, userOriginId);
		
		// 조회해보고 그 여부에 따라 > 삭제 or 추가
		if (count1 > 0) { 
			// 이미 쌓여있음 > 재클릭 > 1삭제
			starMapper.deleteStar1ByMovieIdAndUserOriginId(movieId, userOriginId);
		} else { 
			// 안 쌓여있음 > 클릭 > 1추가
			starMapper.insertStar1ByMovieIdAndUserOriginId(movieId, userOriginId);
		}
	}
	
	// 2번별에 대한 toggle - 클릭시 2, 재클릭시 0
	public void StarToggle2(int movieId, int userOriginId) { 
		// 이미 별점 매겼나 확인
		int count2 = starMapper.selectStarCountByMovieIdAndUserOriginId(movieId, userOriginId);
		
		// 조회해보고 그 여부에 따라 > 삭제 or 추가
		if (count2 == 2) { 
			// 이미 쌓여있음 > 재클릭 > 2삭제
			starMapper.deleteStar2ByMovieIdAndUserOriginId(movieId, userOriginId);
		} else { 
			// 안 쌓여있음 > 클릭 > 2추가
			starMapper.insertStar2ByMovieIdAndUserOriginId(movieId, userOriginId);
		}
	}
	
	// 3번별에 대한 toggle - 클릭시 3, 재클릭시 0
	public void StarToggle3(int movieId, int userOriginId) { 
		// 이미 별점 매겼나 확인
		int count3 = starMapper.selectStarCountByMovieIdAndUserOriginId(movieId, userOriginId);
		
		// 조회해보고 그 여부에 따라 > 삭제 or 추가
		if (count3 == 3) { 
			// 이미 쌓여있음 > 재클릭 > 3삭제
			starMapper.deleteStar3ByMovieIdAndUserOriginId(movieId, userOriginId);
		} else { 
			// 안 쌓여있음 > 클릭 > 3추가
			starMapper.insertStar3ByMovieIdAndUserOriginId(movieId, userOriginId);
		}
	}
	
	// 4번별에 대한 toggle - 클릭시 4, 재클릭시 0
	public void StarToggle4(int movieId, int userOriginId) { 
		// 이미 별점 매겼나 확인
		int count4 = starMapper.selectStarCountByMovieIdAndUserOriginId(movieId, userOriginId);
		
		// 조회해보고 그 여부에 따라 > 삭제 or 추가
		if (count4 == 4) { 
			// 이미 쌓여있음 > 재클릭 > 4삭제
			starMapper.deleteStar4ByMovieIdAndUserOriginId(movieId, userOriginId);
		} else { 
			// 안 쌓여있음 > 클릭 > 4추가
			starMapper.insertStar4ByMovieIdAndUserOriginId(movieId, userOriginId);
		}
	}
	
	// 5번별에 대한 toggle - 클릭시 5, 재클릭시 0
	public void StarToggle5(int movieId, int userOriginId) { 
		// 이미 별점 매겼나 확인
		int count5 = starMapper.selectStarCountByMovieIdAndUserOriginId(movieId, userOriginId);
		
		// 조회해보고 그 여부에 따라 > 삭제 or 추가
		if (count5 == 5) { 
			// 이미 쌓여있음 > 재클릭 > 5삭제
			starMapper.deleteStar5ByMovieIdAndUserOriginId(movieId, userOriginId);
		} else { 
			// 안 쌓여있음 > 클릭 > 5추가
			starMapper.insertStar5ByMovieIdAndUserOriginId(movieId, userOriginId);
		}
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
