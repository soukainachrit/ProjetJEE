package com.gsnotes.bo;

import javax.persistence.*;

/**
 * Represente l'enseignant d'un element
 *
 * @author Fatima El Jaimi
 *
 */

@Entity
public class elementEnseignants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idElementEnseignants;

    private int annee;

    public Long getIdElementEnseignants() {
        return idElementEnseignants;
    }

    public void setIdElementEnseignants(Long idElementEnseignants) {
        this.idElementEnseignants = idElementEnseignants;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    @ManyToOne
    @JoinColumn(name = "idElement")
    private Element element;

    @ManyToOne
    @JoinColumn(name = "idEnseignant")
    private Enseignant enseignant;

}