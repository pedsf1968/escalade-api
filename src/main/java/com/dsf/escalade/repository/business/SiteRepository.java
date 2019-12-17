package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Site;
import com.dsf.escalade.model.business.SiteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {

   @Query("SELECT type FROM Site s WHERE s.id = ?1")
   SiteType getType(Integer id);
}
