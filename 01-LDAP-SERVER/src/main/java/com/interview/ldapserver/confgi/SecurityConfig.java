package com.interview.ldapserver.confgi;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication().contextSource().url("ldap://localhost:10389/o=nit")
		.managerDn("uid=admin,ou=system").managerPassword("secret")
		.and()
		.userSearchBase("ou=users").userSearchFilter("(cn={0})")
		.groupSearchBase("ou=roles").groupRoleAttribute("cn")
		.groupSearchFilter("(uniqueMember={0})")
		.rolePrefix("ROLE_");
	}
	
	@Override	
	public void configure(HttpSecurity http) throws Exception {		
		http.csrf().disable()
		  .authorizeRequests()
		  .anyRequest()
		  .authenticated()
		  .and()
		  .formLogin()
		  .and().logout()
			 .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		
//		http.csrf().disable()
//		  .authorizeRequests()
//		  .antMatchers("/home/welcome")
//		  .permitAll()
//		  .antMatchers("/home/customer")
//		  .hasAnyRole("CUSTOMER")
//		  .antMatchers("/home/admin")
//		  .hasAnyRole("ADMIN")
//		  .anyRequest()
//		  .authenticated()
//		  .and()
//		  .formLogin()
//		  .and().logout()
//			 .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	

	
 }