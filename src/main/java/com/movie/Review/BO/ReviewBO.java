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
	
	// 영화 리뷰 남기기 - db에 insert
	public int addReviewByMovieIdAndUserOriginId(String review, int movieId, int userOriginId, String userNickName) {
		return reviewMapper.insertReviewByMovieIdAndUserOriginId(review, movieId, userOriginId, userNickName);
	}
	
	// 특정 영화의 리뷰들 가져오기 - db에서 여러건 select
	public List<Review> getMovieReviewListByMovieId(int movieId) {
		return reviewMapper.selectMovieReviewListByMovieId(movieId);
	}
}
