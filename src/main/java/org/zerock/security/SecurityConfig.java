package org.zerock.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.java.Log;

@EnableWebSecurity
@Log
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new ZerockUserService();
	}
	
	/*public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
		log.warning("====================configureGlobar============================");
		
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	} 버전업으로 인해 없어도 동작 가능*/
	

	@Override //alt shift s - override - configure(http) 
	protected void configure(HttpSecurity http) throws Exception {
		log.info("--------------------------------------------");
		log.info("configure");
		log.info("--------------------------------------------");
		
		http.formLogin(); //멤버로 가면 deny니까 폼 로그인으로 가게 함
		
		http.rememberMe().tokenValiditySeconds(60*60*24);

	}
	
	

}
