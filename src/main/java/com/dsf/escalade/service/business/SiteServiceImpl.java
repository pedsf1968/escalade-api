package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Site;
import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.repository.business.SiteRepository;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SiteService")
public class SiteServiceImpl implements SiteService {
   private final SiteRepository siteRepository;
   private final TopoService topoService;
   private final SectorService sectorService;
   private final VoieService voieService;
   private final LongueurService longueurService;
   private final UserService userService;

   @Autowired
   public SiteServiceImpl(SiteRepository siteRepository, TopoService topoService, @Lazy SectorService sectorService, @Lazy VoieService voieService, @Lazy LongueurService longueurService, UserService userService) {
      this.siteRepository = siteRepository;
      this.topoService = topoService;
      this.sectorService = sectorService;
      this.voieService = voieService;
      this.longueurService = longueurService;
      this.userService = userService;
   }

   @Override
   public SiteDto getOne(Integer siteId) {
      return entityToDto(siteRepository.getOne(siteId));
   }

   @Override
   public SiteFullDto getFull(Integer siteId) {
      SiteFullDto siteFullDto = new SiteFullDto();
      Site site = siteRepository.getOne(siteId);

      List<SectorDto> sectorDtos = sectorService.findByTopoId(siteId);
      List<SectorFullDto> sectorFullDtos = new ArrayList<>();
      List<VoieDto> voieDtos = voieService.findByParentId(siteId);
      List<VoieFullDto> voieFullDtos = new ArrayList<>();
      List<LongueurDto> longueurDtos = longueurService.findByVoieId(siteId);
      List<LongueurFullDto> longueurFullDtos =new ArrayList<>();

      for(SectorDto s: sectorDtos) {
         sectorFullDtos.add(sectorService.getFull(s.getId()));
      }

      for (VoieDto v: voieDtos){
         voieFullDtos.add(voieService.getFull(v.getId()));
      }

      for (LongueurDto l: longueurDtos) {
         longueurFullDtos.add(longueurService.getFull(l.getId()));
      }


      siteFullDto.setSite(entityToDto(site));
      siteFullDto.setSectorList(sectorFullDtos);
      siteFullDto.setVoieList(voieFullDtos);
      siteFullDto.setLongueurList(longueurFullDtos);

      return siteFullDto;
   }

   @Override
   public List<SiteDto> findAll() {
      List<SiteDto> siteDtos = new ArrayList<>();

      for(Site s: siteRepository.findAll()) {
         siteDtos.add(entityToDto(s));
      }

      return siteDtos;
   }

   @Override
   public SiteDto entityToDto(Site site) {

      if(site != null) {
         SiteDto siteDto = new SiteDto();

         siteDto.setId(site.getId());
         siteDto.setAliasManager(userService.getOne(site.getManagerId()).getAlias());
         siteDto.setName(site.getName());
         siteDto.setType(site.getType().toString());
         siteDto.setLatitude(site.getLatitude());
         siteDto.setLongitude(site.getLongitude());
         siteDto.setPhotoLink(site.getPhotoLink());
         siteDto.setMapLink(site.getMapLink());
         siteDto.setNbComment(site.getNbComment());

         return siteDto;
      }

      return null;
   }

   @Override
   public Site dtoToEntity(SiteDto siteDto) {

      if(siteDto != null) {
         Site site = new Site();

         site.setId(siteDto.getId());
         site.setManagerId (userService.findByAlias(siteDto.getAliasManager()).getId());
         site.setType(SiteType.valueOf(siteDto.getType()));
         site.setLatitude(siteDto.getLatitude());
         site.setLongitude(siteDto.getLongitude());
         site.setName(siteDto.getName());
         site.setPhotoLink(siteDto.getPhotoLink());
         site.setMapLink(siteDto.getMapLink());
         site.setNbComment(siteDto.getNbComment());

         return site;
      }

      return null;
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

   @Override
   public Integer getManagerId(Integer parentId) {
      Integer topoId = this.getTopoId(parentId);
      TopoDto topoDto = topoService.getOne(topoId);
      UserDto userDto = userService.findByAlias(topoDto.getAliasManager());

      return userDto.getId();
   }
}
