package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {

}
