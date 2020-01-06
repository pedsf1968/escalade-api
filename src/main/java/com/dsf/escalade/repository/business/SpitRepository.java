package com.dsf.escalade.repository.business;


import com.dsf.escalade.model.business.Spit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpitRepository extends JpaRepository <Spit, Integer> {
   List<Spit> findByLongueurId(Integer longueurId);
}
