package com.movie.star.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Star {

	// 필드
	private int id;
	private int movieId;
	private int userId;
	private int point;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
