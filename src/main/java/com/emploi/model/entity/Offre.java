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
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "offre")
@Table(name = "offre")
public class Offre {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offre_sequence")
	@SequenceGenerator(name = "offre_sequence", sequenceName = "offre_seq")
	private Long id;

	@Column(name = "titre", nullable = false)
	@Size(min = 5, max = 250, message = "La longueur doit être comprise entre 5 et 205 caractères.")
	@NotBlank(message = "Le champ doit être renseigné")
	private String titre;

	@Column(name = "dtParution", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Le champ doit être renseigné")
	private Date dtParution;

	@Column(name = "pourvu", nullable = false)
	private Boolean pourvu = Boolean.FALSE;

	@Column(name = "salaireBrut")
	private Double salaireBrut;

	@Column(name = "description", nullable = false, length = 1024)
	@Size(min = 20, max = 1024, message = "La longueur doit être comprise entre 20 et 1024 caractères.")
	@NotEmpty(message = "Le champ doit être renseigné")
	private String description;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_contrat", nullable = false)
	@NotNull(message = "Le champ doit être renseigné")
	private Contrat contrat;

	@Column(name = "dureeContrat")
	private Integer dureeContrat;

	@Column(name = "commune")
	@Size(min = 5, max = 50, message = "La longueur doit être comprise entre 5 et 50 caractères.")
	private String commune;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_entreprise")
	private Entreprise entreprise;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id.offre")
	@JsonIgnore
	private List<Candidature> candidatures;

	private int nbCandidature = 0;
	
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

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public Offre() {
		super();
	}

	public Date getDtParution() {
		return dtParution;
	}

	public void setDtParution(Date dtParution) {
		this.dtParution = dtParution;
	}

	public List<Candidature> getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(List<Candidature> candidatures) {
		this.candidatures = candidatures;
	}

	/*public int getNbCandidature() {
		return this.candidatures.size();
	}*/
	
	@PostLoad
    public void computeNbCandidature() {
        this.nbCandidature = this.candidatures.size();
    }

	public int getNbCandidature() {
		return nbCandidature;
	}

	public void setNbCandidature(int nbCandidature) {
		this.nbCandidature = nbCandidature;
	}

	public Offre(String titre, Double salaireBrut, String description, Contrat contrat, Integer dureeContrat,
			String commune, Entreprise entreprise, Date dtParution) {
		super();
		this.titre = titre;
		this.salaireBrut = salaireBrut;
		this.description = description;
		this.contrat = contrat;
		this.dureeContrat = dureeContrat;
		this.commune = commune;
		this.entreprise = entreprise;
		this.dtParution = dtParution;
	}
}