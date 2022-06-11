package com.gsnotes.bo;



import javax.persistence.*;
import java.time.Year;
import java.util.List;


/**
 * Represente un élément d'un module
 * 
 * @author T. BOUDAA
 *
 */

@Entity
@PrimaryKeyJoinColumn(name = "idElement")
public class Element {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMatiere;

	private String nom;

	private String code;

	public List<InscriptionMatiere> getInscription() {
		return Inscription;
	}

	public void setInscription(List<InscriptionMatiere> inscription) {
		Inscription = inscription;
	}

	private double currentCoefficient;

	@ManyToOne
	@JoinColumn(name="idModule")
	private Module module;


	@OneToMany(mappedBy = "element", cascade = CascadeType.ALL , targetEntity = elementEnseignants.class)
	private List<elementEnseignants> Enseignants;

	@OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL , targetEntity = InscriptionMatiere.class)
	private List<InscriptionMatiere> Inscription;


	public Long getIdMatiere() {
		return idMatiere;
	}

	public void setIdMatiere(Long idMatiere) {
		this.idMatiere = idMatiere;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public double getCurrentCoefficient() {
		return currentCoefficient;
	}

	public void setCurrentCoefficient(double currentCoefficient) {
		this.currentCoefficient = currentCoefficient;
	}


	public List<elementEnseignants> getEnseignants() {
		return Enseignants;
	}

	public void setEnseignants(List<elementEnseignants> enseignants) {
		Enseignants = enseignants;
	}
	public Enseignant getCurrentEnseignant(){
		int year = Year.now().getValue();
		for(elementEnseignants enseignant : Enseignants){
			if(enseignant.getAnnee() == 2022){
				return enseignant.getEnseignant();
			}
		}
		return null;
	}
}