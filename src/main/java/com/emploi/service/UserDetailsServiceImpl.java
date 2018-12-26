package com.emploi.service;

import com.emploi.model.entity.Role;
import com.emploi.model.entity.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRep;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.emploi.model.entity.User user = (com.emploi.model.entity.User) userRep.findUserByUsername(username).get();

		Collection<GrantedAuthority> authorities = new ArrayList();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getNom()));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}
}