package com.emploi.controller.rest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emploi.model.entity.User;
import com.emploi.model.entity.repository.UserRepository;

@RestController
@RequestMapping("/api/compte")
@CrossOrigin()
public class CompteRestController {
	@Autowired
	UserRepository compteRep;
	
	@DeleteMapping(value = "/delete/id/{id}")
	public User deleteFromId(@PathVariable Long id) {
		User compte = this.compteRep.findById(id).get();		
		this.compteRep.delete(compte);
		return compte;
	}
	
	@GetMapping(value = "/get/all")
	public List<User> getAll() {
		return StreamSupport.stream(this.compteRep.findAll().spliterator(), false).collect(Collectors.toList());
	}	

	@GetMapping(value = "/get/id/{id}")
	public User getFromId(@PathVariable Long id) {
		return this.compteRep.findById(id).get();
	}	

	@PostMapping(value = "/save")
	public User save(@RequestBody User compte) {
		return this.compteRep.save(compte);
	}
}