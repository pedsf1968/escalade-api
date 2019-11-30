package com.dsf.escalade.service;

import com.dsf.escalade.model.business.Site;
import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.model.business.Topo;
import com.dsf.escalade.repository.business.SiteRepository;
import com.dsf.escalade.repository.business.TopoRepository;
import com.dsf.escalade.repository.global.UserRepository;
import com.dsf.escalade.web.dto.TopoDto;
import org.springframework.beans.factory.annotation.Autowired;

public class TopoServiceImpl implements TopoService{
   private final SiteRepository siteRepository;
   private final TopoRepository topoRepository;
   private final UserRepository userRepository;

   @Autowired
   public TopoServiceImpl(SiteRepository siteRepository, TopoRepository topoRepository, UserRepository userRepository) {
      this.siteRepository = siteRepository;
      this.topoRepository = topoRepository;
      this.userRepository = userRepository;
   }

   @Override
   public TopoDto save(TopoDto topoDto) {
      Site site = new Site();
      Topo topo = new Topo();

      site.setName(topoDto.getName());
      site.setLongitude(topoDto.getLongitude());
      site.setLatitude(topoDto.getLatitude());
      site.setType(SiteType.TOPO);
      site.setPhotoLink(topoDto.getPhotoLink());
      site.setMapLink(topoDto.getMapLink());

      site = siteRepository.save(site);

      topo.setRegion(topoDto.getRegion());
      topo.setDate(topoDto.getDate());
      topo.setDescription(topoDto.getDescription());
      topo.setTechnic(topoDto.getTechnic());
      topo.setManager(userRepository.findByAlias(topoDto.getAlias()).getId());


      return null;
   }
}
