package com.interview.keycloakjwt.controller;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	public String home() {
		return "HOME";
	}
	
	@GetMapping("/secured")
	public Jwt secured(Principal principal) {
		 Jwt user = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        System.out.println(user.getClaimAsString("email") + " " + user.getClaimAsString("name"));
		return user;
	}
	
}
