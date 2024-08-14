package com.movie.Review.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Review {

	// 필드
	private int id;
	private int userId;
	private int movieId;
	private String userNickName;
	private String review;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
