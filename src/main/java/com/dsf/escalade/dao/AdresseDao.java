package com.dsf.escalade.dao;

import com.dsf.escalade.model.global.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseDao extends JpaRepository<Adresse, Integer> {
}
