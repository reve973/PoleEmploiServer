package com.emploi.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emploi.security.model.AuthenticationRequest;
import com.emploi.security.model.JwtTokens;
import com.emploi.security.model.RefreshRequest;
import com.emploi.service.JwtTokenService;

/*
// https://blog.angular-university.io/angular-jwt-authentication/

// init
// curl -i -H "Content-Type: application/json" -X POST -d '{"username": "admin","password": "123"}' http://localhost:8080/api/auth/init

// sans token
// curl http://localhost:8080/api/vehicule/get/all

// avec token
// curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6ImNsaWVudDEiLCJyb2xlcyI6IlJPTEVfQ0xJRU5UIiwidXNlclNlY3JldCI6IlNFQ1JFVCIsImV4cCI6MTUzOTkzMDQ5MSwiaWF0IjoxNTM5OTMwMTkxfQ.urpMPr50llvsp02ZCOod8mgGNB1bGbfaBWBVbOV5qlTEFXgu2fsPWonkGdH69PddxDO5E6hpHv0xk8oxP3qEmw" http://localhost:8080/api/vehicule/get/all
 */


@RestController
@RequestMapping("/api/auth")
public class AuthentificationRestController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenService jwtTokenService;

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password);
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if(authentication != null && authentication.isAuthenticated()) {
			JwtTokens tokens = jwtTokenService.createTokens(authentication);
			
			return ResponseEntity.ok().body(tokens );
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatus.UNAUTHORIZED.getReasonPhrase());
	}
	
	@PostMapping(value = "/refresh", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity refreshToken(@RequestBody RefreshRequest refreshRequest) {
		try {
			JwtTokens tokens = jwtTokenService.refreshJwtToken(refreshRequest.refreshToken);
			return ResponseEntity.ok().body(tokens);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}
	}
}