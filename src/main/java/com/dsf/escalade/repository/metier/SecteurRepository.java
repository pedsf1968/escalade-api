package com.dsf.escalade.repository.metier;

import com.dsf.escalade.model.metier.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecteurRepository extends JpaRepository <Secteur, Integer>  {
}
