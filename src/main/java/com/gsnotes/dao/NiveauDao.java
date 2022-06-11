package com.gsnotes.dao;

import com.gsnotes.bo.Niveau;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauDao extends JpaRepository<Niveau, Long> {

}
