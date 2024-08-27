package com.movie.ReviewList.domain;

import java.util.List;

import com.movie.Review.domain.Review;
import com.movie.movie.domain.Movie;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ReviewCardByUser {

	// 필드 -  영화제목과 그 영화에 남긴 리뷰코멘트
	private List<String> movieName; // 여기서 이따가 영화제목(movieName) 뽑아내면 된다.
	
	private Review review; // 여기서 이따가 리뷰 내용(review) 뽑아내면 된다.
}
