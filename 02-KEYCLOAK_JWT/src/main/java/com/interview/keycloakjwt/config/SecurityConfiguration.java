package com.interview.keycloakjwt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	 @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
	  private String issuer;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable());
		http.authorizeHttpRequests(auth -> {
			auth
			  .requestMatchers("/home")
			  .permitAll()
			  .anyRequest()
			  .authenticated();
		});
		http.oauth2ResourceServer(res -> {
			res.jwt(jwt -> jwtDecoder());
		});
		http.sessionManagement(session->{
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		});
		return http.build();
	}
	
	@Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(issuer);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        jwtDecoder.setJwtValidator(withIssuer);
        return jwtDecoder;
    }

}
