package com.emploi.security.model;

import com.emploi.model.entity.User;

public class JwtTokens {

    public String token;
    public String refreshToken;
    public User user;
    
    public JwtTokens(User user, String token, String refreshToken) {
    	this.user = user;
        this.token = token;
        this.refreshToken = refreshToken;
    }

}