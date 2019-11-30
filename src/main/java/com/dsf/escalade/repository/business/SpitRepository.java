package com.dsf.escalade.repository.business;


import com.dsf.escalade.model.business.Spit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpitRepository extends JpaRepository <Spit, Long> {
}
