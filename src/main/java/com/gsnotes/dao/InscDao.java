package com.gsnotes.dao;

import com.gsnotes.bo.InscriptionMatiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public interface InscDao  extends JpaRepository<InscriptionMatiere, Long> {
    InscriptionMatiere getInscriptionMatiereByInscriptionAnnuelle_Etudiant_IdUtilisateurAndMatiere_IdMatiere(Long idUtilisateur,Long idMatiere);
}
