package com.movie.ReviewList.BO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.metamodel.mapping.internal.AbstractStateArrayContributorMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.Review.BO.ReviewBO;
import com.movie.Review.domain.Review;
import com.movie.ReviewList.domain.ReviewAndStar;
import com.movie.star.BO.StarBO;

@Service
public class ReviewListBO {

	// reviewBO 가져오기
	@Autowired
	private ReviewBO reviewBO;
	
	// starBO 가져오기
	@Autowired
	private StarBO starBO;
	
	public List<ReviewAndStar> generateReviewList(int movieId) {
		List<ReviewAndStar> reviewLists = new ArrayList<>();
		
		// 특정 영화의 리뷰 목록들 가져오기
		List<Review> reviews = reviewBO.getMovieReviewListByMovieId(movieId);
		
		// 리뷰 목록들 반복문
		for (Review review : reviews) {
			
			ReviewAndStar reviewAndStar = new ReviewAndStar();
			
			// 리뷰 내용들, 닉네임들 목록 세팅
			reviewAndStar.setReview(review);
			
			// 별점 세팅
			reviewAndStar.setStarPoints(movieId);
			reviewLists.add(reviewAndStar);
		}
		return reviewLists;
	}
}