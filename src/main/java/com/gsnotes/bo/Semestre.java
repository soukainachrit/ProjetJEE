package com.gsnotes.bo;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Semestre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSemestre;

	private String titre;

	private String Alias;

	public Long getIdSemestre() {
		return idSemestre;
	}

	public void setIdSemestre(Long idSemestre) {
		this.idSemestre = idSemestre;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAlias() {
		return Alias;
	}

	public void setAlias(String alias) {
		Alias = alias;
	}

	public Set<Module> getModules() {
		return Modules;
	}

	public void setModules(Set<Module> modules) {
		Modules = modules;
	}

	@OneToMany(mappedBy = "semestre", cascade = CascadeType.ALL , targetEntity = Module.class)
	private Set<Module> Modules;

}