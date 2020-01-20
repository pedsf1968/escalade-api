package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository <Sector, Integer>  {
   Sector getOne(Integer sectorId);
   List<Sector> findByTopoId(Integer topoId);
   Sector save(Sector sector);
}
