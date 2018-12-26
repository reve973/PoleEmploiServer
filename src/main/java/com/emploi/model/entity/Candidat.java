package com.emploi.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="candidat")
@Table(name="candidat")
public class Candidat {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "candidat_sequence")
	@SequenceGenerator(name = "candidat_sequence", sequenceName = "candidat_sequence")
	private Long id;
	 
	@Column(name = "nom", nullable=false)
	private String nom;

	@Column(name = "prenom", nullable=false)
	private String prenom;

	@Column(name = "email", nullable=false)
	@Email
	private String email;
	 
	@Column(name = "dtNaissance", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtNaissance;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable=true)
	User user;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id.candidat")
	@JsonIgnore
	private List<Candidature> candidatures;
	
	public Candidat() {
		super();
	}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtNaissance() {
		return dtNaissance;
	}

	public void setDtNaissance(Date dtNaissance) {
		this.dtNaissance = dtNaissance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Candidature> getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(List<Candidature> candidatures) {
		this.candidatures = candidatures;
	}

	public Candidat(String nom, String prenom, String email, Date dtNaissance, User user) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dtNaissance = dtNaissance;
		this.user = user;
	} 
}