package com.emploi.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import com.emploi.security.model.JwtTokens;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public abstract interface JwtTokenService {
	public abstract JwtTokens createTokens(Authentication paramAuthentication);

	public abstract String createToken(User paramUser);

	public abstract String createRefreshToken(User paramUser);

	public abstract JwtTokens refreshJwtToken(String paramString);

	public abstract Jws<Claims> validateJwtToken(String paramString);
}