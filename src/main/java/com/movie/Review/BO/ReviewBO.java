package com.movie.Review.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.Review.domain.Review;
import com.movie.Review.mapper.ReviewMapper;
import com.movie.star.mapper.StarMapper;

@Service
public class ReviewBO {

	@Autowired
	private ReviewMapper reviewMapper;
	
	@Autowired
	private StarMapper starMapper;
	
	// 이 유저가 이 영화에 대해 이미 쓴 리뷰가 있는 지 확인 - db에서 select // 리뷰가 이미 있는 경우엔 다시 돌려보내기 위해 만든 거 > 일단 나중에
	public int getReviewCountByMovieIdAndUserOriginId(int movieId, int userOriginId) {
		return reviewMapper.selectReviewCountByMovieIdAndUserOriginId(movieId, userOriginId);
	}
	
	// 영화 리뷰 남기기 - db에 insert
	public int addReviewByMovieIdAndUserOriginId(String review, int movieId, int userOriginId, String userNickName) {
		return reviewMapper.insertReviewByMovieIdAndUserOriginId(review, movieId, userOriginId, userNickName);
	}
	
	// 특정 영화의 리뷰들 가져오기 - db에서 여러건 select
	public List<Review> getMovieReviewListByMovieId(int movieId) {
		return reviewMapper.selectMovieReviewListByMovieId(movieId);
	}
	
	// 특정 영화에 특정 유저가 쓴 리뷰 삭제하기 - db에서 delete
	public int deleteReviewByMovieIdAndUserOriginId(int movieId, int userOriginId) {
		return reviewMapper.deleteReviewByMovieIdAndUserOriginId(movieId, userOriginId);
	}
	
	// 특정 영화에 특정 유저가 매긴 별점이 뜨게끔 하기위해 만든 메소드
	public List<Review> getReviewListAndStarMixByMovieId(int movieId) {
		List<Review> reviewList = reviewMapper.selectMovieReviewListByMovieId(movieId); // 재활용 > 특정 영화의 리뷰들 가져와서 list에 담음
	
		for (Review review : reviewList) {
			int star = starMapper.selectPointByMovieIdAndUserId(movieId, review.getUserId()); // 메소드 이름은 UserOriginId로 되어있지만, 나는 어차피 review의 userId를 가져와서 파라미터로 #{userOriginId}에 넣을 것이기에 큰 문제는 없을 것으로 예상.
			review.setStar(star); // review 도메인에 private int star 필드 추가 하였기에 setStar 할 수 있음.
		}
		return reviewList;
	}
}
