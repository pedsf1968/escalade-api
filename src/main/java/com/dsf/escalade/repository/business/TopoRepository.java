package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.model.business.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopoRepository extends JpaRepository <Topo,Integer>  {
   List<Topo> findByManagerId(Integer id);

   @Query("SELECT t FROM Topo t WHERE t.region = ?1 AND t.status = ?2" )
   List<Topo> findAllFiltered(String region, StatusType status);

   @Query("SELECT t FROM Topo t WHERE t.region = ?1" )
   List<Topo> findAllFiltered(String region);

   @Query("SELECT t FROM Topo t WHERE t.status = ?1" )
   List<Topo> findAllFiltered( StatusType status);

   @Query("SELECT DISTINCT region FROM Topo ORDER BY region")
   List<String> findAllRegion();


}
