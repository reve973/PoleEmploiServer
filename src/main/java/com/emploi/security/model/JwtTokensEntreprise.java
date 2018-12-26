package com.emploi.security.model;

import com.emploi.model.entity.Entreprise;
import com.emploi.model.entity.User;

public class JwtTokensEntreprise extends JwtTokens
{
  public Entreprise entreprise;
  
  public JwtTokensEntreprise(User user, Entreprise entreprise, String token, String refreshToken)
  {
    super(user, token, refreshToken);
    this.entreprise = entreprise;
  }
}