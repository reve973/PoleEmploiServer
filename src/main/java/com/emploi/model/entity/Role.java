package com.emploi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="role")
@Table(name="role")
public class Role {
	 
	public static String ROLE_ADMIN = "ROLE_ADMIN";
	public static String ROLE_CANDIDAT = "ROLE_CANDIDAT";
	public static String ROLE_ENTREPRISE = "ROLE_ENTREPRISE";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "role_sequence")
	@SequenceGenerator(name = "candidat_sequence", sequenceName = "role_sequence")
	private Long id;

	@Column(name = "nom", nullable=false)
	private String nom;
	 
	public Role() {
		super();
	}

	public Role(String nom) {
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