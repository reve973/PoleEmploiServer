package com.emploi.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="candidature")
@Table(name="candidature")
public class Candidature {
	@EmbeddedId
	private CandidatureId id;

	/*@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "candidat_sequence")
	@SequenceGenerator(name = "candidat_sequence", sequenceName = "candidat_sequence")
	private Long id;
	*/
	
	@Column(name = "dtCandidature", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Le champ doit être renseigné")
	private Date dtCandidature;

	public CandidatureId getId() {
		return id;
	}

	public void setId(CandidatureId id) {
		this.id = id;
	}

	public Date getDtCandidature() {
		return dtCandidature;
	}

	public void setDtCandidature(Date dtCandidature) {
		this.dtCandidature = dtCandidature;
	}
	
	public Candidature() {
		super();
	}

	public Candidature(Offre offre, Candidat candidat, Date dtCandidature) {		
		this.id = new CandidatureId(offre, candidat);
		this.dtCandidature = dtCandidature;
	}

	
	public Candidature(CandidatureId id, Date dtCandidature) {
		super();
		this.id = id;
		this.dtCandidature = dtCandidature;
	}	
}