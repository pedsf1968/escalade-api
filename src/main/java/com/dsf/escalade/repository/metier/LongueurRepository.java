package com.dsf.escalade.repository.metier;

import com.dsf.escalade.model.metier.Longueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LongueurRepository extends JpaRepository<Longueur,Integer> {
}
