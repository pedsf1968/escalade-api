package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Voie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoieRepository extends JpaRepository <Voie, Long> {
}
