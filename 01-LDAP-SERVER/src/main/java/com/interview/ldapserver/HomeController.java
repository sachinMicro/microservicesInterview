package com.interview.ldapserver;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {


	@GetMapping("/welcome")

	public String welcome() {		
		return "welcome";
	}
	
	@GetMapping("/customer")
	//@Secured("hasAnyRole('CUSTOMER')")
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	public String customer() {
		return "customer";
	}
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String admin() {
		return "admin";
	}
}
