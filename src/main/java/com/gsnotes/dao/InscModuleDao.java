package com.gsnotes.dao;

import com.gsnotes.bo.InscriptionMatiere;
import com.gsnotes.bo.InscriptionModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscModuleDao extends JpaRepository<InscriptionModule, Long> {

    InscriptionModule getInscriptionModuleByInscriptionAnnuelle_Etudiant_IdUtilisateurAndAndModule_IdModule(Long idUtilisateur,Long idModule);
    List<InscriptionModule> getInscriptionModulesByModule_IdModule(Long IdModule);

}
