package com.dsf.escalade.dao.impl;

import com.dsf.escalade.dao.contract.SiteDao;
import com.dsf.escalade.model.metier.Site;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.dsf.escalade.model.metier.Site.SiteType.TOPO;

@Repository
@Slf4j
public class SiteDaoImpl implements SiteDao {

   public static List <Site> sites = new ArrayList<>();

   static {
      sites.add(new Site(1,"La falaise", TOPO, Boolean.FALSE,"",""));
      sites.add(new Site(2,"Le Rock", TOPO, Boolean.FALSE,"",""));
      sites.add(new Site(3,"La gorge", TOPO, Boolean.FALSE,"",""));
   }

   @Override
   public List<Site> findAll() {
      return sites;
   }

   @Override
   public Site findById(int id) {
      for (Site site : sites) {
         if (site.getId() == id)
            return  site;
      }

      return null;
   }

   @Override
   public Site save(Site site) {
      sites.add(site);

      return site;
   }
}
