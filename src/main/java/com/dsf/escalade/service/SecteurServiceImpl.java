package com.dsf.escalade.service;

import com.dsf.escalade.model.business.Secteur;
import com.dsf.escalade.repository.business.SecteurRepository;
import com.dsf.escalade.web.dto.SecteurDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SecteurService")
public class SecteurServiceImpl implements SecteurService{

   private final SecteurRepository secteurRepository;

   public SecteurServiceImpl(SecteurRepository secteurRepository) {
      this.secteurRepository = secteurRepository;
   }

   @Override
   public List<SecteurDto> findAll() {
      List<SecteurDto> secteurDtoList = new ArrayList<>();
      SecteurDto secteurDto = null;

      for(Secteur secteur: secteurRepository.findAll()){
         secteurDto = new SecteurDto();
         secteurDto.setId(secteur.getId());
         secteurDto.setTopoId(secteur.getTopoId());
         secteurDto.setName(secteur.getName());
         secteurDto.setEquipement(secteur.getEquipement());
         secteurDto.setHasComment(secteur.getHasComment());
         secteurDto.setLatitude(secteur.getLatitude());
         secteurDto.setLongitude(secteur.getLongitude());
         secteurDto.setPhotoLink(secteur.getPhotoLink());
         secteurDto.setMapLink(secteur.getMapLink());

         secteurDtoList.add(secteurDto);
      }

      return secteurDtoList;
   }

   @Override
   public List<SecteurDto> findByTopoId(Integer id) {
      List<SecteurDto> secteurDtoList = new ArrayList<>();
      SecteurDto secteurDto = null;

      for(Secteur secteur: secteurRepository.findByTopoId(id)){
         secteurDto = new SecteurDto();
         secteurDto.setId(secteur.getId());
         secteurDto.setTopoId(secteur.getTopoId());
         secteurDto.setName(secteur.getName());
         secteurDto.setEquipement(secteur.getEquipement());
         secteurDto.setHasComment(secteur.getHasComment());
         secteurDto.setLatitude(secteur.getLatitude());
         secteurDto.setLongitude(secteur.getLongitude());
         secteurDto.setPhotoLink(secteur.getPhotoLink());
         secteurDto.setMapLink(secteur.getMapLink());

         secteurDtoList.add(secteurDto);
      }

      return secteurDtoList;
   }

   @Override
   public SecteurDto getOne(Integer id) {
      SecteurDto secteurDto = new SecteurDto();
      Secteur secteur = secteurRepository.getOne(id);

      secteurDto.setId(secteur.getId());
      secteurDto.setTopoId(secteur.getTopoId());
      secteurDto.setName(secteur.getName());
      secteurDto.setEquipement(secteur.getEquipement());
      secteurDto.setHasComment(secteur.getHasComment());
      secteurDto.setLatitude(secteur.getLatitude());
      secteurDto.setLongitude(secteur.getLongitude());
      secteurDto.setPhotoLink(secteur.getPhotoLink());
      secteurDto.setMapLink(secteur.getMapLink());
      return secteurDto;
   }

   @Override
   public Integer save(SecteurDto secteurDto) {
      Secteur secteur =new Secteur();

      secteur.setId(secteurDto.getId());
      secteur.setTopoId(secteurDto.getTopoId());
      secteur.setName(secteurDto.getName());
      secteur.setEquipement(secteurDto.getEquipement());
      secteur.setHasComment(secteurDto.getHasComment());
      secteur.setLatitude(secteurDto.getLatitude());
      secteur.setLongitude(secteurDto.getLongitude());
      secteur.setPhotoLink(secteurDto.getPhotoLink());
      secteur.setMapLink(secteurDto.getMapLink());

      return secteurRepository.save(secteur).getId();
   }
}
