package com.sample.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(nullable=false, length=20, unique=true)
	private String userId;
	private String password;
	private String userName;
	private String email;
	
	public String getUserId() {
		return userId;
	}
	public boolean matchId(Long paramId) {
		if (paramId == null) {
			return false;
		}
		
		return paramId.equals(id);
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	
	public boolean matchPassword(String paramPassword) {
		if (paramPassword == null) {
			return false;
		}
		
		if (paramPassword.equals(password)) {
			return true;
		}
		return false;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", userName=" + userName + ", email=" + email + "]";
	}
	public void update(User newUser) {
		this.userName = newUser.userName;
		this.email = newUser.email;
		
	}
	
	
}
