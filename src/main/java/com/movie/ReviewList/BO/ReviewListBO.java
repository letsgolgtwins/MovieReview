package com.movie.ReviewList.BO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.metamodel.mapping.internal.AbstractStateArrayContributorMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.Review.BO.ReviewBO;
import com.movie.Review.domain.Review;
import com.movie.ReviewList.domain.ReviewCard;
import com.movie.ReviewList.domain.ReviewCardByUser;
import com.movie.movie.BO.MovieBO;
import com.movie.star.BO.StarBO;
import com.movie.star.domain.Star;

import jakarta.servlet.http.HttpSession;

@Service
public class ReviewListBO {

	// reviewBO 가져오기
	@Autowired
	private ReviewBO reviewBO;
	
	// starBO 가져오기
	@Autowired
	private StarBO starBO;
	
	// movieBO 가져오기 (0827)
	@Autowired
	private MovieBO movieBO;
	
	// 특정 영화의 리뷰 리스트 페이지
	public List<ReviewCard> generateReviewCardList(int movieId) {
		List<ReviewCard> reviewLists = new ArrayList<>();
		
		// 특정 영화의 리뷰 목록들 가져오기
		List<Review> reviewInfo = reviewBO.getMovieReviewListByMovieId(movieId);
		
		// 리뷰 목록들 반복문
		for (Review review : reviewInfo) {
			ReviewCard reviewCard = new ReviewCard();
			
			// 리뷰 내용들, 닉네임들 목록 세팅
			reviewCard.setReview(review);
			
			// 별점 가져오기 
			Integer starPoints = starBO.getPointByMovieIdAndUserId(movieId, review.getUserId());
			
			// 별점 세팅
			reviewCard.setStarPoints(starPoints);
			
			// 저장
			reviewLists.add(reviewCard);
		}
		return reviewLists;
	}
	
	// 특정 유저가 남긴 리뷰 코멘트들 리스트 페이지(0827)
	public List<ReviewCardByUser> generateReviewCardListByUser(int userOriginId) {
		List<ReviewCardByUser> reviewLists = new ArrayList<>();
		
		// 유저가 남긴 리뷰 정보들 목록 가져오기
		List<Review> reviewInfo = reviewBO.getReviewInfoByUserOriginId(userOriginId);
		
		// 리뷰 목록들 반복문 돌리기
		for (Review review : reviewInfo) {
			ReviewCardByUser reviewCardByUser = new ReviewCardByUser();
			
			// 리뷰 내용들 세팅
			reviewCardByUser.setReview(review);
			
			// 영화 제목 가져오기
			
		}
	}
	
}