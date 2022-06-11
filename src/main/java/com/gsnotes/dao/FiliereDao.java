package com.gsnotes.dao;

import com.gsnotes.bo.Compte;
import com.gsnotes.bo.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereDao extends JpaRepository<Filiere, Long> {
}
