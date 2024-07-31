package com.interview.keycloak.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	
	@GetMapping("/login")
	public OidcUser secured(@AuthenticationPrincipal OidcUser user){
		System.out.println(user.getEmail());
		return user;
	}
	
	@GetMapping("/welcome")
	public String welcome(){
		//System.out.println(user.getEmail());
		return "welcome";
	}
	
	
}
