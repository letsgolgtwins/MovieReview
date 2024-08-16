package com.movie.ReviewList.domain;

import java.util.List;

import com.movie.Review.domain.Review;
import com.movie.star.domain.Star;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ReviewAndStar {

	// 리뷰 내용
	private Review review;
	
	// 리뷰의 별점이 몇점인가
	//private Star starGrade;
	private int starGrade;
}
