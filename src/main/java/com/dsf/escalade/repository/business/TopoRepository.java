package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopoRepository extends JpaRepository <Topo,Integer>  {
   public List<Topo> findByManagerId(Integer id);
}
