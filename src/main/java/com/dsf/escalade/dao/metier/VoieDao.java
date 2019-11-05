package com.dsf.escalade.dao.metier;

import com.dsf.escalade.model.metier.Voie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoieDao extends JpaRepository <Voie, Integer> {
}
