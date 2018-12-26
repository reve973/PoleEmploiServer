package com.emploi.model.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CandidatureId implements Serializable {
	@ManyToOne
    @JoinColumn(name="id_offre")
    private Offre offre;

	@ManyToOne
    @JoinColumn(name="id_candidat")
    private Candidat candidat;

	public Offre getOffre() {
		return offre;
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	public CandidatureId() {
		super();
	}

	public CandidatureId(Offre offre, Candidat candidat) {
		super();
		this.offre = offre;
		this.candidat = candidat;
	}		
}