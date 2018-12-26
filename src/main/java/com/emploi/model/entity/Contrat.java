package com.emploi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="contrat")
@Table(name="contrat")
public class Contrat {
	public static String CONTRAT_CDI = "CDI";
	public static String CONTRAT_CDD = "CDD";
	public static String CONTRAT_CDDI = "CDDI";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "contrat_sequence")
	@SequenceGenerator(name = "contrat_sequence", sequenceName = "contrat_seq")
	private Long id;
	 
	@Column(name = "nom")
	private String nom;

	public Contrat() {
		super();
	}

	public Contrat(String nom) {
		super();
		this.nom = nom;
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
}