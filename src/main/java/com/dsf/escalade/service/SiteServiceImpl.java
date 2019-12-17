package com.dsf.escalade.service;

import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.repository.business.SiteRepository;
import org.springframework.stereotype.Service;

@Service("SiteService")
public class SiteServiceImpl implements SiteService {
   private final SiteRepository siteRepository;

   public SiteServiceImpl(SiteRepository siteRepository) {
      this.siteRepository = siteRepository;
   }

   @Override
   public SiteType getType(Integer id) {

      return siteRepository.getType(id);
   }
}
