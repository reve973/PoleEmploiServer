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

import com.emploi.model.entity.Candidat;
import com.emploi.model.entity.repository.CandidatRepository;

@RestController
@RequestMapping("/api/candidat")
@CrossOrigin()
public class CandidatRestController {
	@Autowired
	CandidatRepository candidatRep;
	
	@DeleteMapping(value = "/delete/id/{id}")
	public Candidat deleteFromId(@PathVariable Long id) {
		Candidat candidat = this.candidatRep.findById(id).get();		
		this.candidatRep.delete(candidat);
		return candidat;
	}
	
	@GetMapping(value = "/get/all")
	public List<Candidat> getAll() {
		return StreamSupport.stream(this.candidatRep.findAll().spliterator(), false).collect(Collectors.toList());
	}	

	@GetMapping(value = "/get/id/{id}")
	public Candidat getFromId(@PathVariable Long id) {
		return this.candidatRep.findById(id).get();
	}	

	@PostMapping(value = "/save")
	public Candidat save(@RequestBody Candidat candidat) {
		return this.candidatRep.save(candidat);
	}
}