package com.emploi.controller.rest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emploi.model.entity.User;
import com.emploi.model.entity.repository.UserDetailsRepository;
import com.emploi.model.entity.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
@CrossOrigin()
public class UserRestController {
	@Autowired
	UserRepository userRep;
	
	@Autowired
	UserDetailsRepository userDetailsRep;

	@DeleteMapping(value = "/delete/id/{id}")
	public User deleteFromId(@PathVariable Long id) {
		User user = this.userRep.findById(id).get();		
		this.userRep.delete(user);
		return user;
	}
	
	@GetMapping(value = "/get/all")
	public List<User> getAll() {
		return StreamSupport.stream(this.userRep.findAll().spliterator(), false).collect(Collectors.toList());
	}	

	@GetMapping(value = "/get/id/{id}")
	public User getFromId(@PathVariable Long id) {
		return this.userRep.findById(id).get();
	}	


	
	@PostMapping(value = "/save")
	public User save(@RequestBody User user) {
		return this.userRep.save(user);
	}
	
	@GetMapping(value = "/details/get/all")
	public List<UserDetails> getAllUserDetails() {
		return this.userDetailsRep.findAll();
	}	
}