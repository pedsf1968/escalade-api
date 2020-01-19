package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Sector;
import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.repository.business.SectorRepository;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.dto.SectorDto;
import com.dsf.escalade.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SectorService")
public class SectorServiceImpl implements SectorService {

   private final SectorRepository sectorRepository;
   private final UserService userService;

   @Autowired
   public SectorServiceImpl(SectorRepository sectorRepository, UserService userService) {
      this.sectorRepository = sectorRepository;
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
         sector.setManagerId(userService.findByAlias(sectorDto.getAliasManager()).getId());
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
   public SectorDto getOne(Integer id) {
      Sector sector = sectorRepository.getOne(id);

      return entityToDto(sector);
   }

   @Override
   public Integer save(SectorDto sectorDto) {
      Sector sector = dtoToEntity(sectorDto);

      return sectorRepository.save(sector).getId();
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

}
