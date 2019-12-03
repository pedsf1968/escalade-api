package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecteurRepository extends JpaRepository <Secteur, Integer>  {
   List<Secteur> findByTopoId(Integer id);
}
