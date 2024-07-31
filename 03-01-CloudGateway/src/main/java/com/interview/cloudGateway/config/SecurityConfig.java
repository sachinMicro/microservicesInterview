package com.interview.cloudGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, ReactiveClientRegistrationRepository clientRegistrationRepository) {
        http.authorizeExchange(auth -> 
                        auth
                         .pathMatchers("/auth/login","/loggedout")
                         .permitAll()
                         .anyExchange()
                         .authenticated())
                //REMOVE LOGIN PAGE TO MAKE IT AS STATELESS
               // .oauth2Login(Customizer.withDefaults())
                .logout(logout->{
                	logout.logoutSuccessHandler(keycloakLogoutSuccessHandler(clientRegistrationRepository));
                })
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
       
       // http.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());
        
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
    
 
    //@Bean
	public ServerLogoutSuccessHandler keycloakLogoutSuccessHandler(ReactiveClientRegistrationRepository repository) {

        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(repository);

        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/loggedout");

        return oidcLogoutSuccessHandler;
    }

}