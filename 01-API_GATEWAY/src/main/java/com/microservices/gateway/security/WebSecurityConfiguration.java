package com.microservices.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfiguration {
	
	
	@Bean
	SecurityWebFilterChain  securityWebFilterChain(ServerHttpSecurity  http) throws Exception {
		http.authorizeExchange(auth->{
			auth.anyExchange().authenticated();
		})
		.oauth2Login()
		.and()
		.oauth2ResourceServer(res->{
		res.jwt();
		});

		return http.build();
	}

}
