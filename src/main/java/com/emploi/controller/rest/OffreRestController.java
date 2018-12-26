package com.emploi.controller.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emploi.model.entity.Entreprise;
import com.emploi.model.entity.Offre;
import com.emploi.model.entity.repository.EntrepriseRepository;
import com.emploi.model.entity.repository.OffreRepository;

@RestController
@RequestMapping("/api/offre")
@CrossOrigin()
public class OffreRestController {
	@Autowired
	OffreRepository offreRep;
	
	@Autowired
	EntrepriseRepository entrepriseRep;
	
	@DeleteMapping(value = "/delete/id/{id}")
	public Offre deleteFromId(@PathVariable Long id) {
		Offre offre = this.offreRep.findById(id).get();		
		this.offreRep.deleteById(id);
		return offre;
	}
	
	@PostMapping(value = "/entreprise/{idEntreprise}/save")
	public Offre create(@PathVariable Long idEntreprise, @RequestBody Offre offre) {
		Entreprise entreprise = this.entrepriseRep.findById(idEntreprise).get();
		offre.setEntreprise(entreprise);		
		return this.offreRep.save(offre);
	}

	@GetMapping(value = "/entreprise/{idEntreprise}/get/all/page")
	public Page<Offre> getPageByEntreprise( @PathVariable Long idEntreprise,	
			@RequestParam(name="motclef", defaultValue="")String motclef, 
			@RequestParam(name="commune", defaultValue="")String commune, 
			@RequestParam(name="cdd", defaultValue="false")Boolean cdd,
			@RequestParam(name="cddi", defaultValue="false")Boolean cddi,
			@RequestParam(name="cdi", defaultValue="false")Boolean cdi,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="5")int size,
			@RequestParam(name="sortcolumn", defaultValue="titre")String sortColumn,
			@RequestParam(name="sortorder", defaultValue="asc")String sortOrder) {

		List<String> contrats = new ArrayList<String>();

		if(!cdd && !cdi && !cddi) {
			contrats = Arrays.asList("CDDI", "CDD", "CDI");
		}
		
		if (cdd) {
			contrats.add("CDD");
		}
			
		if (cddi) {
			contrats.add("CDDI");
		}

		if (cdi) {
			contrats.add("CDI");
		}

		return this.offreRep.findPageByEntreprise(idEntreprise,"%" + motclef + "%", "%" + commune + "%", contrats, PageRequest.of(page, size, new Sort(sortOrder.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC, sortColumn)));
	}	 
	
	@GetMapping(value = "/get/all/page")
	public Page<Offre> getPage(			@RequestParam(name="motclef", defaultValue="")String motclef, 
			@RequestParam(name="commune", defaultValue="")String commune, 
			@RequestParam(name="cdd", defaultValue="false")Boolean cdd,
			@RequestParam(name="cddi", defaultValue="false")Boolean cddi,
			@RequestParam(name="cdi", defaultValue="false")Boolean cdi,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="5")int size,
			@RequestParam(name="sortcolumn", defaultValue="titre")String sortColumn,
			@RequestParam(name="sortorder", defaultValue="asc")String sortOrder) {

		List<String> contrats = new ArrayList<String>();

		if(!cdd && !cdi && !cddi) {
			contrats = Arrays.asList("CDDI", "CDD", "CDI");
		}
		
		if (cdd) {
			contrats.add("CDD");
		}
			
		if (cddi) {
			contrats.add("CDDI");
		}

		if (cdi) {
			contrats.add("CDI");
		}

		return this.offreRep.findPage("%" + motclef + "%", "%" + commune + "%", contrats, PageRequest.of(page, size, new Sort(sortOrder.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC, sortColumn)));
	}	 

	@GetMapping(value = "/get/all")
	public List<Offre> getAll() {
		return StreamSupport.stream(this.offreRep.findAll().spliterator(), false).collect(Collectors.toList());
	}	

	@GetMapping(value = "/get/id/{id}")
	public Offre getFromId(@PathVariable Long id) {
		return this.offreRep.findById(id).get();
	}	

	@PostMapping(value = "/update")
	public Offre save(@RequestBody Offre offre) {
		return this.offreRep.save(offre);
	}
}