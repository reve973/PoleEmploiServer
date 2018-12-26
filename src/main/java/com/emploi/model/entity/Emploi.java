package com.emploi.model.entity;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity(name="Emploi")
@Table(name="Emploi")
public class Emploi {

	 @Id
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "emploi_sequence")
	 @SequenceGenerator(name = "emploi_sequence", sequenceName = "emploi_seq")
	 private Long id;
	 
	 @Column(name = "titre")
	 private String titre;
	
	 @Column(name = "pourvu")
	 private Boolean pourvu;
	 
	 @Column(name = "dtOuverture")
	 @Basic
	 private Date dtOuvertue;
	 
	 @Column(name = "dtCloture")
	 @Basic
	 private Date dtCloture;
	 
	 @Column(name = "salaireBrut")
	 private Double salaireBrut;	 
	 
	 @Column(name = "description")
	 private String description;
	 
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_contrat", nullable = false)
	@NotNull(message = "Le champ doit être renseigné")
	private Contrat contrat;	  
	 
	 @Column(name = "dureeContrat")
	 private Integer dureeContrat;
	 
	 @Column(name = "commune")
	 private String commune;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Boolean getPourvu() {
		return pourvu;
	}

	public void setPourvu(Boolean pourvu) {
		this.pourvu = pourvu;
	}

	public Date getDtOuvertue() {
		return dtOuvertue;
	}

	public void setDtOuvertue(Date dtOuvertue) {
		this.dtOuvertue = dtOuvertue;
	}

	public Date getDtCloture() {
		return dtCloture;
	}

	public void setDtCloture(Date dtCloture) {
		this.dtCloture = dtCloture;
	}

	public Double getSalaireBrut() {
		return salaireBrut;
	}

	public void setSalaireBrut(Double salaireBrut) {
		this.salaireBrut = salaireBrut;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public Integer getDureeContrat() {
		return dureeContrat;
	}

	public void setDureeContrat(Integer dureeContrat) {
		this.dureeContrat = dureeContrat;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}
}