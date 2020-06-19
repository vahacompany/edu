package com.sample.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User extends AbstractEntity {

	@Column(nullable=false, length=20, unique=true)
	@JsonProperty
	private String userId;

	@JsonIgnore
	private String password;

	@JsonProperty
	private String userName;

	@JsonProperty
	private String email;


	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean matchId(Long newId) {
		if (newId == null) {
			return false;
		}
		
		return newId.equals(getId());
	}
	
	public boolean matchPassword(String newPassword) {
		if (newPassword == null) {
			return false;
		}
		
		return newPassword.equals(password);
	}
	
	public void setName(String userName) {
		this.userName = userName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void update(User newUser) {
		this.password = newUser.password;
		this.userName = newUser.userName;
		this.email = newUser.email;
	}

	@Override
	public String toString() {
		return "User [id=" + super.toString() + ", userId=" + userId + ", password=" + password + ", userName=" + userName + ", email="
				+ email + "]";
	}
}
