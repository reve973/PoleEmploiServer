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

import com.emploi.model.entity.Contrat;
import com.emploi.model.entity.repository.ContratRepository;

@RestController
@RequestMapping("/api/contrat")
@CrossOrigin()
public class ContratRestController {
	@Autowired
	ContratRepository contratRep;
	
	@DeleteMapping(value = "/delete/id/{id}")
	public Contrat deleteFromId(@PathVariable Long id) {
		Contrat contrat = this.contratRep.findById(id).get();		
		this.contratRep.delete(contrat);
		return contrat;
	}
	
	@GetMapping(value = "/get/all")
	public List<Contrat> getAll() {
		return StreamSupport.stream(this.contratRep.findAll().spliterator(), false).collect(Collectors.toList());
	}	

	@GetMapping(value = "/get/id/{id}")
	public Contrat getFromId(@PathVariable Long id) {
		return this.contratRep.findById(id).get();
	}	

	@PostMapping(value = "/save")
	public Contrat save(@RequestBody Contrat contrat) {
		return this.contratRep.save(contrat);
	}
}