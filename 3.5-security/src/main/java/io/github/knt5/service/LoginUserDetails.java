package io.github.knt5.service;

import org.springframework.security.core.authority.AuthorityUtils;

import io.github.knt5.domain.User;

public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
	private final User user;
	
	public LoginUserDetails(User user) {
		super(user.getUsername(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		
		this.user = user;
	}
}
