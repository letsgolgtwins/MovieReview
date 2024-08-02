package com.movie.User.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
//@Data
public class User {

	// 필드
	private int id;
	private String userId;
	private String userPassword;
	private String userName;
	private String userNickName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public int getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
