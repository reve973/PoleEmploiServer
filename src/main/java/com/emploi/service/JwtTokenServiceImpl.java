package com.emploi.service;

import com.emploi.model.entity.Candidat;
import com.emploi.model.entity.Entreprise;
import com.emploi.model.entity.Role;
import com.emploi.model.entity.repository.CandidatRepository;
import com.emploi.model.entity.repository.EntrepriseRepository;
import com.emploi.model.entity.repository.UserRepository;
import com.emploi.security.model.JwtTokens;
import com.emploi.security.model.JwtTokensCandidat;
import com.emploi.security.model.JwtTokensEntreprise;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenServiceImpl
  implements JwtTokenService
{
  private static final String USER_SECRET = "userSecret";
  private String secret = "secret";
  @Autowired
  private UserDetailsService userService;
  @Autowired
  private UserRepository userRep;
  @Autowired
  private EntrepriseRepository entRep;
  @Autowired
  private CandidatRepository candRep;
  
  public JwtTokens createTokens(Authentication authentication)
  {
    org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
    
    String token = createToken(user);
    String refreshToken = createRefreshToken(user);
    
    com.emploi.model.entity.User _user = (com.emploi.model.entity.User)userRep.findUserByUsername(user.getUsername()).get();
    if (_user.getRole().getNom().equals("ROLE_ENTREPRISE"))
    {
      Entreprise _ent = entRep.findEntrepriseByUsername(_user.getUsername());
      return new JwtTokensEntreprise(_user, _ent, token, refreshToken);
    }
    if (_user.getRole().getNom().equals("ROLE_CANDIDAT"))
    {
      Candidat _cand = candRep.findCandidatByUsername(_user.getUsername());
      return new JwtTokensCandidat(_user, _cand, token, refreshToken);
    }
    return new JwtTokens(_user, token, refreshToken);
  }
  
  public String createToken(org.springframework.security.core.userdetails.User user)
  {
    return 
    
      Jwts.builder().signWith(SignatureAlgorithm.HS512, secret).setClaims(buildUserClaims(user)).setExpiration(getTokenExpirationDate(false)).setIssuedAt(new Date()).compact();
  }
  
  public String createRefreshToken(org.springframework.security.core.userdetails.User user)
  {
    return 
    
      Jwts.builder().signWith(SignatureAlgorithm.HS512, secret).setClaims(buildUserClaims(user)).setExpiration(getTokenExpirationDate(true)).setIssuedAt(new Date()).compact();
  }
  
  public Jws<Claims> validateJwtToken(String token)
  {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
  }
  
  public JwtTokens refreshJwtToken(String refreshToken)
  {
    Jws<Claims> claims = validateJwtRefreshToken(refreshToken);
    String newToken = createTokenFromClaims(claims);
    return new JwtTokens(null, newToken, refreshToken);
  }
  
  private String createTokenFromClaims(Jws<Claims> jws)
  {
    return 
    
      Jwts.builder().setClaims((Claims)jws.getBody()).signWith(SignatureAlgorithm.HS512, secret).setExpiration(getTokenExpirationDate(false)).setIssuedAt(new Date()).compact();
  }
  
  private Jws<Claims> validateJwtRefreshToken(String token)
  {
    JwtParser parser = Jwts.parser().setSigningKey(secret);
    Jws<Claims> claims = parser.parseClaimsJws(token);
    
    org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)userService.loadUserByUsername(((Claims)claims.getBody()).getSubject());
    
    return parser.require("userSecret", "SECRET").parseClaimsJws(token);
  }
  
  private Date getTokenExpirationDate(boolean refreshToken)
  {
    Calendar calendar = Calendar.getInstance();
    if (refreshToken) {
      calendar.add(2, 1);
    } else {
      calendar.add(12, 5);
    }
    return calendar.getTime();
  }
  
  private Claims buildUserClaims(org.springframework.security.core.userdetails.User user)
  {
    Claims claims = new DefaultClaims();
    
    claims.put("username", user.getUsername());
    
    claims.put("roles", String.join(",", AuthorityUtils.authorityListToSet(user.getAuthorities())));
    claims.put("userSecret", "SECRET");
    
    return claims;
  }
}
