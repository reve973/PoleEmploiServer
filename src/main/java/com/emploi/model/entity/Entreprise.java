package com.emploi.model.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name="entreprise")
@Table(name="entreprise")
public class Entreprise {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "entreprise_sequence")
	@SequenceGenerator(name = "entreprise_sequence", sequenceName = "entreprise_seq")
	private Long id;
	 
	@Column(name = "denom", nullable=false)
	private String denom;
	
	@Column(name = "telephone", nullable=false)
	private String telephone;
	 
	@Column(name = "email", nullable=false)
	private String email;
	 
	@Column(name = "siteweb", nullable=false)
	private String siteweb;
	 
	@Column(name = "description", nullable=false)
	private String description;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable=true)
    private User user;
	
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "entreprise")
    @JsonIgnore
    private List<Offre> offres;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenom() {
		return denom;
	}

	public void setDenom(String denom) {
		this.denom = denom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSiteweb() {
		return siteweb;
	}

	public void setSiteweb(String siteweb) {
		this.siteweb = siteweb;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}

	public Entreprise() {
		super();
	}

	public Entreprise(String denom, String telephone, String email, String siteweb, String description, User user) {
		super();
		this.denom = denom;
		this.telephone = telephone;
		this.email = email;
		this.siteweb = siteweb;
		this.description = description;
		this.user = user;
	}
}