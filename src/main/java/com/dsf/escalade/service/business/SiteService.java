package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Site;
import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.web.dto.SiteDto;
import com.dsf.escalade.web.dto.SiteFullDto;

import java.util.List;

public interface SiteService {
   // repository override methods
   SiteDto getOne(Integer siteId);
   List<SiteDto> findAll();

   // Complete versions
   SiteFullDto getFull(Integer siteId);

   // converters
   SiteDto entityToDto(Site site);
   Site dtoToEntity(SiteDto siteDto);

   // utilities methods
   SiteType getType(Integer id);
   Integer getTopoId(Integer parentId);
   Integer getManagerId(Integer parentId);
}
