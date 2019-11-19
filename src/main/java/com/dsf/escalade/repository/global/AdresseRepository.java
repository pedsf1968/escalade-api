package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {

}
