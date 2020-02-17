package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Topo;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopoRepository extends JpaRepository <Topo,Integer>, JpaSpecificationExecutor {
   Topo getOne(@NotNull Integer topoId);

   List<Topo> findByManagerId(Integer id);
   List<Topo> findByClimberId(Integer id);

   @Query("SELECT DISTINCT region FROM Topo ORDER BY region")
   List<String> findAllRegion();

   @Query("SELECT DISTINCT u.alias FROM User u INNER JOIN Topo t ON t.managerId = u.id")
   List<String> findAllAlias();


}
