package com.emploi.controller.rest;

import java.util.Date;

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
import com.emploi.model.entity.Candidature;
import com.emploi.model.entity.CandidatureId;
import com.emploi.model.entity.Offre;
import com.emploi.model.entity.repository.CandidatRepository;
import com.emploi.model.entity.repository.CandidatureRepository;
import com.emploi.model.entity.repository.OffreRepository;

@RestController
@RequestMapping("/api/candidature")
@CrossOrigin()
public class CandidatureRestController {
	@Autowired
	CandidatureRepository candidatureRep;
	
	@Autowired
	OffreRepository offreRep;

	@Autowired
	CandidatRepository candidatRep;

	/*@DeleteMapping(value = "/delete/user/{idUser}/offre/{idOffre}")
	public Candidat deleteFromId(@PathVariable Long idUser, @PathVariable Long idOffre) {
		Candidat candidat = this.candidatRep.findById(id).get();		
		this.candidatRep.delete(candidat);
		return candidat;
	}*/	
	
	/*@PostMapping(value = "/save")
	public Candidature save(@RequestBody Candidature candidature) {
		return this.candidatureRep.save(candidature);
	}*/
	
	@GetMapping(value = "/offre/{idOffre}/candidat/{idCandidat}/get")
	public Candidature get(@PathVariable Long idOffre, @PathVariable Long idCandidat) {
		return this.candidatureRep.findById(idOffre, idCandidat).get();
	}	
	
	@PostMapping(value = "/save")
	public Candidature save(@RequestBody CandidatureId candidatureId) {
				
		Offre offre = this.offreRep.findById(candidatureId.getOffre().getId()).get();
		Candidat candidat = this.candidatRep.findById(candidatureId.getCandidat().getId()).get();
		
		Candidature candidature = new Candidature(offre, candidat, new Date());
		
		return this.candidatureRep.save(candidature);
	}

	@DeleteMapping(value = "/offre/{idOffre}/candidat/{idCandidat}/delete")
	public Boolean delete(@PathVariable Long idOffre, @PathVariable Long idCandidat) {		
		this.candidatureRep.deleteById(idOffre,idCandidat);
		
		return !this.candidatureRep.findById(idOffre, idCandidat).isPresent();
	}
}