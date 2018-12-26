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

import com.emploi.model.entity.Entreprise;
import com.emploi.model.entity.repository.EntrepriseRepository;

@RestController
@RequestMapping("/api/entreprise")
@CrossOrigin()
public class EntrepriseRestController {
	@Autowired
	EntrepriseRepository entrepriseRep;
	
	@DeleteMapping(value = "/delete/id/{id}")
	public Entreprise deleteFromId(@PathVariable Long id) {
		Entreprise entreprise = this.entrepriseRep.findById(id).get();		
		this.entrepriseRep.delete(entreprise);
		return entreprise;
	}
	
	@GetMapping(value = "/get/all")
	public List<Entreprise> getAll() {
		return StreamSupport.stream(this.entrepriseRep.findAll().spliterator(), false).collect(Collectors.toList());
	}	

	@GetMapping(value = "/get/id/{id}")
	public Entreprise getFromId(@PathVariable Long id) {
		return this.entrepriseRep.findById(id).get();
	}	

	@PostMapping(value = "/save")
	public Entreprise save(@RequestBody Entreprise entreprise) {
		return this.entrepriseRep.save(entreprise);
	}
}