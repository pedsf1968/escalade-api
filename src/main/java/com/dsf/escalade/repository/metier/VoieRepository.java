package com.dsf.escalade.repository.metier;

import com.dsf.escalade.model.metier.Voie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoieRepository extends JpaRepository <Voie, Integer> {
}
