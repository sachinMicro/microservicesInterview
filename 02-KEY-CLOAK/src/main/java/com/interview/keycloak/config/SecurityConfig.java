package com.interview.keycloak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain  configure(HttpSecurity http) throws Exception {
		
		http
		  .authorizeHttpRequests(auth->{
			  auth
			    .requestMatchers("/")
			    .permitAll()
			    .requestMatchers("/home")
			    .permitAll()
			    .anyRequest()
			    .authenticated();
		  });
		http
		   .oauth2Login(Customizer.withDefaults());

		http
		   .logout(logout->{
			   logout.invalidateHttpSession(true);
			   logout.logoutSuccessUrl("http://localhost:8080/realms/testrealm/protocol/openid-connect/logout");
		 });
		
		
		return http.build();
	}
	
}
