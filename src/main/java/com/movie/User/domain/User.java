package com.movie.User.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class User {

	// 필드
	private int id;
	private String userId;
	private String userPassword;
	private String userName;
	private String userNickName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
