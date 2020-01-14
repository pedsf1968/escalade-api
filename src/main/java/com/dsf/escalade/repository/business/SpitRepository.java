package com.dsf.escalade.repository.business;


import com.dsf.escalade.model.business.Spit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpitRepository extends JpaRepository <Spit, Integer> {
   List<Spit> findByLongueurId(Integer longueurId);

   Spit findFirstBySpitPKTopoIdOrderBySpitPKNumberDesc(Integer topoId);

   Spit findBySpitPKTopoIdAndSpitPKNumber(Integer spitPKTopoId, Integer spitPKNumber);

   @Query("SELECT SUM(s.cotationId)/COUNT(*) FROM Spit s WHERE s.spitPK.topoId = ?1 AND s.voieId = ?2 AND s.longueurId = ?3")
   Integer getLongueurCotationAverage(Integer topoId, Integer laneId, Integer lengthId);

   @Query("SELECT SUM(s.cotationId)/COUNT(*) FROM Spit s WHERE s.spitPK.topoId = ?1 AND s.voieId = ?2")
   Integer getVoieCotationAverage(Integer topoId, Integer laneId);

   @Query("SELECT SUM(s.cotationId)/COUNT(*) FROM Spit s WHERE s.spitPK.topoId = ?1")
   Integer getTopoCotationAverage(Integer topoId);

   @Query("SELECT MAX(s.cotationId) FROM Spit s WHERE s.spitPK.topoId = ?1")
   Integer getCotationIdMaxBySpitPKTopoId(Integer topoId);

   @Query("SELECT MIN(s.cotationId) FROM Spit s WHERE s.spitPK.topoId = ?1")
   Integer getCotationIdMinBySpitPKTopoId(Integer topoId);
}
