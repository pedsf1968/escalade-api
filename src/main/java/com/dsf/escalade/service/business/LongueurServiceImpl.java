package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Longueur;
import com.dsf.escalade.repository.business.LongueurRepository;
import com.dsf.escalade.web.dto.LongueurDto;
import com.dsf.escalade.web.dto.VoieDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("LongueurService")
public class LongueurServiceImpl implements LongueurService {
   private final SiteService siteService;
   private final VoieService voieService;
   private final LongueurRepository longueurRepository;
   private final SpitService spitService;

   public LongueurServiceImpl(SiteService siteService, VoieService voieService, LongueurRepository longueurRepository, SpitService spitService) {
      this.siteService = siteService;
      this.voieService = voieService;
      this.longueurRepository = longueurRepository;
      this.spitService = spitService;
   }

   @Override
   public LongueurDto entityToDto(Longueur longueur) {
      if(longueur == null){
         return null;
      }

      LongueurDto longueurDto = new LongueurDto();

      longueurDto.setId(longueur.getId());
      longueurDto.setVoieId(longueur.getVoieId());
      longueurDto.setCotationId(longueur.getCotationId());
      longueurDto.setHeigth(longueur.getHeigth());
      longueurDto.setName(longueur.getName());

      return longueurDto;
   }

   @Override
   public Longueur dtoToEntity(LongueurDto longueurDto) {
      if(longueurDto==null){
         return null;
      }

      Longueur longueur = new Longueur();

      longueur.setId(longueurDto.getId());
      longueur.setVoieId(longueurDto.getVoieId());
      longueur.setCotationId(longueurDto.getCotationId());
      longueur.setHeigth(longueurDto.getHeigth());
      longueur.setName(longueurDto.getName());

      return longueur;
   }

   @Override
   public LongueurDto getOne(Integer id) {
      Longueur longueur = longueurRepository.getOne(id);

      return entityToDto(longueur);
   }

   @Override
   public List<LongueurDto> findByVoieId(Integer id) {
      List<LongueurDto> longueurDtos = new ArrayList<>();

      for(Longueur longueur : longueurRepository.findByVoieId(id)){
         longueurDtos.add(entityToDto(longueur));
      }

      return longueurDtos;
   }

   @Override
   public Integer save(LongueurDto longueurDto) {
      Longueur longueur = dtoToEntity(longueurDto);

      return longueurRepository.save(longueur).getId();
   }

   @Override
   public Integer delete(LongueurDto longueurDto) {
      Integer longueurId = longueurDto.getId();

      if(longueurId!=null){
         longueurRepository.delete(longueurRepository.getOne(longueurId));
         return longueurId;
      }

      return null;
   }

   @Override
   public void updateCotation(LongueurDto longueurDto){
      Integer cotationId;
      VoieDto voieDto = voieService.getOne(longueurDto.getVoieId());
      Integer topoId = siteService.getTopoId(voieDto.getParentId());

      cotationId = spitService.getLongueurCotationAverage(topoId, voieDto.getId(), longueurDto.getId());
      longueurDto.setCotationId(cotationId);
      save(longueurDto);
      voieService.updateCotation(voieDto);
   }
}
