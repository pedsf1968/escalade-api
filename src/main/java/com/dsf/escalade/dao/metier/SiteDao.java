package com.dsf.escalade.dao.metier;

import com.dsf.escalade.model.metier.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteDao extends JpaRepository <Site, Integer> {
}
