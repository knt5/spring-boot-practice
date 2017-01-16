package io.github.knt5;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity
				.ignoring()
				.antMatchers("/webjars/**", "/assets/**");
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.authorizeRequests()
					.antMatchers("/loginForm")
						.permitAll()
					.anyRequest()
						.authenticated()
				.and()
					.formLogin()
						.loginProcessingUrl("/login")
						.loginPage("/loginForm")
						.failureUrl("/loginForm?error")
						.defaultSuccessUrl("/customers", true)
						.usernameParameter("username")
						.passwordParameter("password")
				.and()
					.logout()  // default: POST /logout
						.logoutSuccessUrl("/loginForm");
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new Pbkdf2PasswordEncoder();
	}
}
