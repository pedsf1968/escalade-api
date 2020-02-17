package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Sector;
import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.repository.business.SectorRepository;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SectorService")
public class SectorServiceImpl implements SectorService {

   private final SectorRepository sectorRepository;
   private final SiteService siteService;
   private final VoieService voieService;
   private final UserService userService;

   @Autowired
   public SectorServiceImpl(SectorRepository sectorRepository, SiteService siteService, @Lazy VoieService voieService, UserService userService) {
      this.sectorRepository = sectorRepository;
      this.siteService = siteService;
      this.voieService = voieService;
      this.userService = userService;
   }

   @Override
   public SectorDto entityToDto(Sector sector) {
      if(sector==null){
         return null;
      }

      SectorDto sectorDto = new SectorDto();
      sectorDto.setId(sector.getId());
      sectorDto.setTopoId(sector.getTopoId());
      sectorDto.setName(sector.getName());
      sectorDto.setEquipment(sector.getEquipment());
      sectorDto.setNbComment(sector.getNbComment());
      sectorDto.setLatitude(sector.getLatitude());
      sectorDto.setLongitude(sector.getLongitude());
      sectorDto.setPhotoLink(sector.getPhotoLink());
      sectorDto.setMapLink(sector.getMapLink());

      if (sector.getManagerId() != null) {
         sectorDto.setAliasManager(userService.getOne(sector.getManagerId()).getAlias());
      }

      return sectorDto;
   }

   @Override
   public Sector dtoToEntity(SectorDto sectorDto) {
      if(sectorDto==null){
         return null;
      }

      Sector sector =new Sector();

      sector.setId(sectorDto.getId());
      sector.setTopoId(sectorDto.getTopoId());
      sector.setName(sectorDto.getName());
      sector.setType(SiteType.SECTOR);
      sector.setEquipment(sectorDto.getEquipment());
      sector.setNbComment(sectorDto.getNbComment());
      sector.setLatitude(sectorDto.getLatitude());
      sector.setLongitude(sectorDto.getLongitude());
      sector.setPhotoLink(sectorDto.getPhotoLink());
      sector.setMapLink(sectorDto.getMapLink());

      if (sectorDto.getAliasManager() != null) {
         UserDto userDto = userService.findByAlias(sectorDto.getAliasManager());
         if(userDto!=null) {
            sector.setManagerId(userDto.getId());
         } else {
            // set topo manager default manager
            sector.setManagerId(siteService.getManagerId(sectorDto.getTopoId()));
         }
      }

      return sector;
   }

   @Override
   public List<SectorDto> findAll() {
      List<SectorDto> sectorDtos = new ArrayList<>();

      for(Sector sector : sectorRepository.findAll()){
         sectorDtos.add(entityToDto(sector));
      }

      return sectorDtos;
   }

   @Override
   public List<SectorDto> findByTopoId(Integer id) {
      List<SectorDto> sectorDtos = new ArrayList<>();

      for(Sector sector : sectorRepository.findByTopoId(id)){
         sectorDtos.add(entityToDto(sector));
      }

      return sectorDtos;
   }

   @Override
   public SectorDto getOne(Integer sectorId) {
      Sector sector = sectorRepository.getOne(sectorId);

      return entityToDto(sector);
   }

   @Override
   public Integer save(SectorDto sectorDto) {
      Sector sector = dtoToEntity(sectorDto);
      sector = sectorRepository.save(sector);

      return sector.getId();
   }

   @Override
   public Integer delete(SectorDto sectorDto) {
      Integer sectorId = sectorDto.getId();

      if(sectorId!=null){
         sectorRepository.delete(sectorRepository.getOne(sectorId));
         return sectorId;
      }

      return null;
   }

   @Override
   public Boolean hasRight(SectorDto sectorDto){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      UserDto userDto = userService.findByAlias(sectorDto.getAliasManager());

      if (userDto.getEmail().equals(authentication.getName())) {
         return Boolean.TRUE;
      }

      return Boolean.FALSE;
   }

   @Override
   public SectorFullDto getFull(Integer sectorId) {
      SectorFullDto sectorFullDto = new SectorFullDto();
      List<VoieDto> voieDtos = voieService.findByParentId(sectorId);
      List<VoieFullDto> voieFullDtos = new ArrayList<>();

      for (VoieDto v: voieDtos){
         voieFullDtos.add(voieService.getFull(v.getId()));
      }

      sectorFullDto.setSector(this.getOne(sectorId));
      sectorFullDto.setVoieList(voieFullDtos);

      return sectorFullDto;
   }

   @Override
   public Integer saveFull(SectorFullDto sectorFullDto) {
      Integer oldSectorId = sectorFullDto.getSector().getId();
      Integer sectorId = this.save(sectorFullDto.getSector());

      // save voie of the sector
      for(VoieFullDto v: sectorFullDto.getVoieList()){
         if(sectorId!=oldSectorId) {
            // change id of the voie if the sector is new
            v.getVoie().setId(null);
            v.getVoie().setParentId(sectorId);
         }
         voieService.saveFull(v);
      }

      return sectorId;
   }
}
