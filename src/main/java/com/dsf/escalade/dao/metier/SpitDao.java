package com.dsf.escalade.dao.metier;


import com.dsf.escalade.model.metier.Spit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpitDao extends JpaRepository <Spit, Integer> {
}
