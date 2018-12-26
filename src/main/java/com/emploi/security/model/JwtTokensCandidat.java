package com.emploi.security.model;

import com.emploi.model.entity.Candidat;
import com.emploi.model.entity.User;

public class JwtTokensCandidat extends JwtTokens {
	public Candidat candidat;

	public JwtTokensCandidat(User user, Candidat candidat, String token, String refreshToken) {
		super(user, token, refreshToken);
		this.candidat = candidat;
	}
}