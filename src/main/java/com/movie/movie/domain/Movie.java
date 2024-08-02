package com.movie.movie.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Movie {

	private int id;
	private String moviePoster;
	private String movieName;
	private int movieYear;
	private String movieGenre;
	private String movieNation;
	private String movieDirector;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
