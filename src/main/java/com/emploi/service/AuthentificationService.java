package com.emploi.service;


import org.springframework.security.core.Authentication;

import com.emploi.security.model.AuthenticationRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface AuthentificationService {

    Authentication getAuthentication(Jws<Claims> request);

    Authentication authenticate(AuthenticationRequest authenticationRequest);

}
