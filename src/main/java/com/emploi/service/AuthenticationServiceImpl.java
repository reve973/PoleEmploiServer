package com.emploi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.emploi.security.model.AuthenticationRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Service
public class AuthenticationServiceImpl implements AuthentificationService {
	@Autowired
	private AuthenticationManager authenticationManager;

	public Authentication authenticate(AuthenticationRequest authenticationRequest) {
		UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password);
		return authenticationManager.authenticate(usernameAuthentication);
	}

	public Authentication getAuthentication(Jws<Claims> token) {
		return new UsernamePasswordAuthenticationToken(((Claims) token.getBody()).getSubject(), "PROTECTED",
				AuthorityUtils.commaSeparatedStringToAuthorityList(
						(String) ((Claims) token.getBody()).get("roles", String.class)));
	}
}