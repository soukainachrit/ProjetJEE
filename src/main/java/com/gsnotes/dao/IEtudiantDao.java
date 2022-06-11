package com.gsnotes.dao;

import com.gsnotes.bo.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEtudiantDao extends JpaRepository<Etudiant, Long> {
}
