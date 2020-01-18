package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Longueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LongueurRepository extends JpaRepository<Longueur,Integer> {
   Longueur getOne(Integer longueurId);
   List<Longueur> findByVoieId(Integer voieId);
}
