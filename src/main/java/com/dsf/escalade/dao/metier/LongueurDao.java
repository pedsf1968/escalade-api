package com.dsf.escalade.dao.metier;

import com.dsf.escalade.model.metier.Longueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LongueurDao extends JpaRepository<Longueur,Integer> {
}
