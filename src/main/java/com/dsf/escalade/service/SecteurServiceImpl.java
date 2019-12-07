package com.dsf.escalade.service;

import com.dsf.escalade.model.business.Sector;
import com.dsf.escalade.repository.business.SectorRepository;
import com.dsf.escalade.web.dto.SecteurDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SecteurService")
public class SecteurServiceImpl implements SecteurService{

   private final SectorRepository sectorRepository;

   public SecteurServiceImpl(SectorRepository sectorRepository) {
      this.sectorRepository = sectorRepository;
   }

   @Override
   public List<SecteurDto> findAll() {
      List<SecteurDto> secteurDtoList = new ArrayList<>();
      SecteurDto secteurDto = null;

      for(Sector sector : sectorRepository.findAll()){
         secteurDto = new SecteurDto();
         secteurDto.setId(sector.getId());
         secteurDto.setTopoId(sector.getTopoId());
         secteurDto.setName(sector.getName());
         secteurDto.setEquipement(sector.getEquipement());
         secteurDto.setHasComment(sector.getHasComment());
         secteurDto.setLatitude(sector.getLatitude());
         secteurDto.setLongitude(sector.getLongitude());
         secteurDto.setPhotoLink(sector.getPhotoLink());
         secteurDto.setMapLink(sector.getMapLink());

         secteurDtoList.add(secteurDto);
      }

      return secteurDtoList;
   }

   @Override
   public List<SecteurDto> findByTopoId(Integer id) {
      List<SecteurDto> secteurDtoList = new ArrayList<>();
      SecteurDto secteurDto = null;

      for(Sector sector : sectorRepository.findByTopoId(id)){
         secteurDto = new SecteurDto();
         secteurDto.setId(sector.getId());
         secteurDto.setTopoId(sector.getTopoId());
         secteurDto.setName(sector.getName());
         secteurDto.setEquipement(sector.getEquipement());
         secteurDto.setHasComment(sector.getHasComment());
         secteurDto.setLatitude(sector.getLatitude());
         secteurDto.setLongitude(sector.getLongitude());
         secteurDto.setPhotoLink(sector.getPhotoLink());
         secteurDto.setMapLink(sector.getMapLink());

         secteurDtoList.add(secteurDto);
      }

      return secteurDtoList;
   }

   @Override
   public SecteurDto getOne(Integer id) {
      SecteurDto secteurDto = new SecteurDto();
      Sector sector = sectorRepository.getOne(id);

      secteurDto.setId(sector.getId());
      secteurDto.setTopoId(sector.getTopoId());
      secteurDto.setName(sector.getName());
      secteurDto.setEquipement(sector.getEquipement());
      secteurDto.setHasComment(sector.getHasComment());
      secteurDto.setLatitude(sector.getLatitude());
      secteurDto.setLongitude(sector.getLongitude());
      secteurDto.setPhotoLink(sector.getPhotoLink());
      secteurDto.setMapLink(sector.getMapLink());
      return secteurDto;
   }

   @Override
   public Integer save(SecteurDto secteurDto) {
      Sector sector =new Sector();

      sector.setId(secteurDto.getId());
      sector.setTopoId(secteurDto.getTopoId());
      sector.setName(secteurDto.getName());
      sector.setEquipement(secteurDto.getEquipement());
      sector.setHasComment(secteurDto.getHasComment());
      sector.setLatitude(secteurDto.getLatitude());
      sector.setLongitude(secteurDto.getLongitude());
      sector.setPhotoLink(secteurDto.getPhotoLink());
      sector.setMapLink(secteurDto.getMapLink());

      return sectorRepository.save(sector).getId();
   }
}
