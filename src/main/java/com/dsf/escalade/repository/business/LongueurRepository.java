package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Longueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LongueurRepository extends JpaRepository<Longueur,Long> {
}
