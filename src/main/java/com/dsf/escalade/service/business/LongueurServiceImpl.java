package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Longueur;
import com.dsf.escalade.repository.business.LongueurRepository;
import com.dsf.escalade.web.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("LongueurService")
public class LongueurServiceImpl implements LongueurService {
   private final SiteService siteService;
   private final VoieService voieService;
   private final LongueurRepository longueurRepository;
   private final SpitService spitService;

   public LongueurServiceImpl(@Lazy SiteService siteService,@Lazy VoieService voieService,@Lazy LongueurRepository longueurRepository,@Lazy SpitService spitService) {
      this.siteService = siteService;
      this.voieService = voieService;
      this.longueurRepository = longueurRepository;
      this.spitService = spitService;
   }

   @Override
   public LongueurDto entityToDto(Longueur longueur) {

      if(longueur != null){
         LongueurDto longueurDto = new LongueurDto();

         longueurDto.setId(longueur.getId());
         longueurDto.setVoieId(longueur.getVoieId());
         longueurDto.setCotationId(longueur.getCotationId());
         longueurDto.setHeigth(longueur.getHeigth());
         longueurDto.setName(longueur.getName());

         return longueurDto;
      }

      return null;
   }

   @Override
   public Longueur dtoToEntity(LongueurDto longueurDto) {

      if(longueurDto!=null){
         Longueur longueur = new Longueur();

         longueur.setId(longueurDto.getId());
         longueur.setVoieId(longueurDto.getVoieId());
         longueur.setCotationId(longueurDto.getCotationId());
         longueur.setHeigth(longueurDto.getHeigth());
         longueur.setName(longueurDto.getName());

         return longueur;
      }

      return null;
   }

   @Override
   public LongueurDto getOne(Integer id) {

      Longueur longueur = longueurRepository.getOne(id);

      return entityToDto(longueur);
   }

   @Override
   public List<LongueurDto> findByVoieId(Integer voieId) {
      List<LongueurDto> longueurDtos = new ArrayList<>();

      for(Longueur longueur : longueurRepository.findByVoieId(voieId)){
         longueurDtos.add(entityToDto(longueur));
      }

      return longueurDtos;
   }

   @Override
   public Integer save(LongueurDto longueurDto) {
      Longueur longueur = dtoToEntity(longueurDto);
      longueur = longueurRepository.save(longueur);

      return longueur.getId();
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
      VoieDto voieDto = voieService.getOne(longueurDto.getVoieId());
      Integer topoId = siteService.getTopoId(voieDto.getParentId());
      Integer cotationId = spitService.getLongueurCotationAverage(topoId, voieDto.getId(), longueurDto.getId());

      log.info("New cotation average :" + cotationId);
      longueurDto.setCotationId(cotationId);
      this.save(longueurDto);
      voieService.updateCotation(voieDto);
   }

   @Override
   public LongueurFullDto getFull(Integer longueurId) {
      LongueurDto longueurDto = getOne(longueurId);
      LongueurFullDto longueurFullDto = new LongueurFullDto();

      longueurFullDto.setLongueur(longueurDto);
      longueurFullDto.setSpitList(spitService.findByLongueurId(longueurId));

      return longueurFullDto;
   }

   @Override
   public Integer saveFull(LongueurFullDto longueurFullDto) {
      LongueurDto longueurDto = longueurFullDto.getLongueur();
      Integer oldLongueurId = longueurDto.getId();
      Integer longueurId = this.save(longueurFullDto.getLongueur());
      Integer voieId;
      List<SpitDto> spitDtos = longueurFullDto.getSpitList().getSpitDtos();


      if(longueurId!=oldLongueurId){
         for(SpitDto s:spitDtos){
            voieId = longueurDto.getVoieId();
            s.setLongueurId(longueurId);
            s.setVoieId(voieId);
            s.setTopoId(siteService.getTopoId(voieId));
         }
      }

      SpitDtoList spitDtoList = new SpitDtoList();
      spitDtoList.setSpitDtos(spitDtos);

      spitService.saveAll(spitDtoList);
      this.save(longueurDto);
      this.updateCotation(longueurDto);

      return longueurId;
   }
}
