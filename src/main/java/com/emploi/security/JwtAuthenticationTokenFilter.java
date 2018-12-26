package com.emploi.security;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.emploi.service.AuthentificationService;
import com.emploi.service.JwtTokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

@Component
public class JwtAuthenticationTokenFilter
  extends GenericFilterBean
{
  private static final String BEARER = "Bearer";
  @Autowired
  private AuthentificationService authentificationService;
  @Autowired
  private JwtTokenService jwtTokenService;
  
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest request = (HttpServletRequest)servletRequest;
    HttpServletResponse response = (HttpServletResponse)servletResponse;
    
    Optional<String> token = Optional.ofNullable(request.getHeader("Authorization"));
    if ((token.isPresent()) && (((String)token.get()).startsWith("Bearer")))
    {
      String bearerToken = ((String)token.get()).substring("Bearer".length() + 1);
      try
      {
        Jws<Claims> claims = jwtTokenService.validateJwtToken(bearerToken);
        Authentication authentication = authentificationService.getAuthentication(claims);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
      catch (ExpiredJwtException exception)
      {
        response.sendError(401, "error.jwt.expired");
        return;
      }
      catch (JwtException exception)
      {
        response.sendError(401, "error.jwt.invalid");
        return;
      }
    }
    chain.doFilter(servletRequest, servletResponse);
    SecurityContextHolder.getContext().setAuthentication(null);
  }
}