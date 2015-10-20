package com.rw.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

//@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication)throws AuthenticationException {
		// TODO Auto-generated method stub
		
		
		return authentication;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		
		return true;
	}

}
