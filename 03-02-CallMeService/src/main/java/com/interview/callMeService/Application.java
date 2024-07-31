package com.interview.callMeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController	
@RequestMapping("/callme")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
   // @PreAuthorize("hasAuthority('SCOPE_TEST')")
    @GetMapping("/ping")
    public String ping() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return "Scopes: " + authentication.getAuthorities();
    }

}
