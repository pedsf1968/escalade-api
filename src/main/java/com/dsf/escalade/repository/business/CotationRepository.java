package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Cotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotationRepository extends JpaRepository<Cotation,Integer> {
   List<Cotation> findAll();
}
