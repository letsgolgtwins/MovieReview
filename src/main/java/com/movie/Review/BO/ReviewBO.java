package com.movie.Review.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.Review.mapper.ReviewMapper;

@Service
public class ReviewBO {

	@Autowired
	private ReviewMapper reviewMapper;
	
	// 영화 리뷰 남기기 - db에 insert
	public int addReviewByMovieIdAndUserOriginId(String review, int movieId, int userOriginId) {
		return reviewMapper.insertReviewByMovieIdAndUserOriginId(review, movieId, userOriginId);
	}
}
