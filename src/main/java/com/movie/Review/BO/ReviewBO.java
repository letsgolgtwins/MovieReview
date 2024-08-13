package com.movie.Review.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.Review.domain.Review;
import com.movie.Review.mapper.ReviewMapper;

@Service
public class ReviewBO {

	@Autowired
	private ReviewMapper reviewMapper;
	
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
}
