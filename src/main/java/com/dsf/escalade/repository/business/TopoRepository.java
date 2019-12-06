package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Topo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TopoRepository extends JpaRepository <Topo,Integer>  {
   public List<Topo> findByManagerId(Integer id);
}
