package net.notetalking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginResponse {
	private String access_token;
	private String message;
	
	@JsonIgnore
	public LoginResponse(String access_token) {
		this.access_token = access_token;
	}

	public LoginResponse(String access_token, String message) {
		this.access_token = access_token;
		this.message = message;
	}
	
	
}
