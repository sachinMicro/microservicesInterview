package com.interview.keycloak.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@GetMapping(path = {"/","/home"})
    public String home() {
         return "Not secured"; 
    }
	
	@GetMapping("/secured")
	public OidcUser secured(
			@AuthenticationPrincipal OidcUser user){
		System.out.println(user.getEmail());
		return user;
	}
	

}