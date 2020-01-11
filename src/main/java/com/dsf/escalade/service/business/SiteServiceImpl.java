package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Site;
import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.repository.business.SiteRepository;
import com.dsf.escalade.web.dto.SectorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SiteService")
public class SiteServiceImpl implements SiteService {
   private final SiteRepository siteRepository;
   private final SectorService sectorService;

   @Autowired
   public SiteServiceImpl(SiteRepository siteRepository, SectorService sectorService) {
      this.siteRepository = siteRepository;
      this.sectorService = sectorService;
   }

   @Override
   public SiteType getType(Integer id) {

      return siteRepository.getType(id);
   }

   @Override
   public Integer getTopoId(Integer parentId) {
      Site site = siteRepository.getOne(parentId);

      if(site.getType().equals(SiteType.SECTOR)){
         SectorDto sectorDto = sectorService.getOne(parentId);
         parentId = sectorDto.getTopoId();
      }

      return parentId;
   }

}
