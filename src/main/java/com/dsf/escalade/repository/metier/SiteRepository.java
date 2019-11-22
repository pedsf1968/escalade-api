package com.dsf.escalade.repository.metier;

import com.dsf.escalade.model.metier.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {

}
