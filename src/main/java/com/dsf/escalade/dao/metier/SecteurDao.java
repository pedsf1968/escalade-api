package com.dsf.escalade.dao.metier;

import com.dsf.escalade.model.metier.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecteurDao extends JpaRepository <Secteur, Integer>  {
}
