package com.movie.ReviewList.domain;

import java.util.Map;

import com.movie.Review.domain.Review;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ReviewCard {

	// 리뷰 내용
	private Review review;
	
	// 리뷰의 별점이 몇점인가
	private Integer starPoints;
}
