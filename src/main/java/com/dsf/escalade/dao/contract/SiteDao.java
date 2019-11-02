package com.dsf.escalade.dao.contract;

import com.dsf.escalade.model.metier.Site;

import java.util.List;

public interface SiteDao {
   public List<Site> findAll();
   public Site findById(int id);
   public Site save(Site site);
}
