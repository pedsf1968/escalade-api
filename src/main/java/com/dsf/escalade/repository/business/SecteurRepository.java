package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecteurRepository extends JpaRepository <Secteur, Long>  {
}
