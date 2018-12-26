package com.emploi.model.entity.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsRepository {
	@Autowired
	EntityManager em;

	public List<UserDetails> findAll() {
		return em.createQuery("SELECT new com.emploi.model.UserDetails(u, 'Entreprise', e.denom) FROM user u, entreprise e WHERE e.user.id=u.id union SELECT new com.emploi.model.UserDetails(u, 'Entreprise', e.denom) FROM user u, entreprise e WHERE e.user.id=u.id", UserDetails.class).getResultList();
	}
}
