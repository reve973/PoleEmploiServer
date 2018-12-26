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

import com.emploi.model.entity.Role;
import com.emploi.model.entity.repository.RoleRepository;

@RestController
@RequestMapping("/api/role")
@CrossOrigin()
public class RoleRestController {
	@Autowired
	RoleRepository RoleRep;
	
	@DeleteMapping(value = "/delete/id/{id}")
	public Role deleteFromId(@PathVariable Long id) {
		Role role = this.RoleRep.findById(id).get();		
		this.RoleRep.delete(role);
		return role;
	}
	
	@GetMapping(value = "/get/all")
	public List<Role> getAll() {
		return StreamSupport.stream(this.RoleRep.findAll().spliterator(), false).collect(Collectors.toList());
	}	

	@GetMapping(value = "/get/id/{id}")
	public Role getFromId(@PathVariable Long id) {
		return this.RoleRep.findById(id).get();
	}	

	@PostMapping(value = "/save")
	public Role save(@RequestBody Role role) {
		return this.RoleRep.save(role);
	}
}