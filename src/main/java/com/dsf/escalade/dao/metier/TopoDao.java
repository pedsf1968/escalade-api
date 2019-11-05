package com.dsf.escalade.dao.metier;

import com.dsf.escalade.model.metier.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopoDao extends JpaRepository <Topo,Integer>  {
}
