package com.interview.cloudGateway.response;

import lombok.Data;

@Data
public class LoginResponse {

	private String access_token;
	private String refresh_token;
	
};
