package com.interview.cloudGateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.interview.cloudGateway.response.LoginRequest;
import com.interview.cloudGateway.response.LoginResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {
	
	
	@Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
	private String CLIENT_ID;

	@Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
	private String CLIENT_SECRET;

	@Value("${keycloak.token.path}")
	private String TOKEN_URI;
	
	@Value("${keycloak.grant_type}")
	private String GRANT_TYPE;
	
	@Autowired
	RestTemplate restTemplate;


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest,
			@RequestHeader(name = "hey", required = false) String customHeader) {
		
		System.out.println("LOGIN REQUEST {} "+loginRequest);
	
		System.out.println("LOGIN REQUEST {} "+customHeader);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("username",loginRequest.getUsername());
		map.add("password",loginRequest.getPassword());
		map.add("client_id",CLIENT_ID);
		map.add("client_secret",CLIENT_SECRET);
		map.add("grant_type",GRANT_TYPE);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

		ResponseEntity<LoginResponse> response =
				restTemplate.exchange(TOKEN_URI,
		                          HttpMethod.POST,
		                          entity,
		                          LoginResponse.class);
		
		HttpHeaders headers2 = new HttpHeaders();
		headers2.set("Authorization","Bearer "+response.getBody().getAccess_token());
		HttpEntity<String> entity2 = new HttpEntity<String>(headers2);
		
		ResponseEntity<String> reponseStr = restTemplate.exchange("http://localhost:8040/callme/ping",
                HttpMethod.GET,
                entity2,
                String.class);
		
		return ResponseEntity.status(HttpStatus.OK).body(reponseStr); 
	}
	 
	
}
