package org.dnyanyog.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddUserResponse {

	private String status;
	private String message;
	private String userId;

	@Autowired
	public UserData userData;

	public static AddUserResponse getInstance() {
		return new AddUserResponse();
	}

	public UserData getUserData() {
		return userData;
	}

	public AddUserResponse setUserData(UserData userData) {
		this.userData = userData;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public AddUserResponse setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public AddUserResponse setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public AddUserResponse setMessage(String message) {
		this.message = message;
		return this;
	}
}
