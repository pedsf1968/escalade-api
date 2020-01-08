package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Longueur;
import com.dsf.escalade.repository.business.LongueurRepository;
import com.dsf.escalade.web.dto.LongueurDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("LongueurService")
public class LongueurServiceImpl implements LongueurService {
   private final LongueurRepository longueurRepository;

   @Autowired
   public LongueurServiceImpl(LongueurRepository longueurRepository) {
      this.longueurRepository = longueurRepository;
   }

   @Override
   public LongueurDto getOne(Integer id) {
      Longueur longueur = longueurRepository.getOne(id);
      LongueurDto longueurDto = new LongueurDto();

      longueurDto.setId(longueur.getId());
      longueurDto.setVoieId(longueur.getVoieId());
      longueurDto.setCotationId(longueur.getCotationId());
      longueurDto.setHeigth(longueur.getHeigth());
      longueurDto.setName(longueur.getName());

      return longueurDto;
   }

   @Override
   public List<LongueurDto> findByVoieId(Integer id) {
      List<Longueur> longueurs = longueurRepository.findByVoieId(id);
      List<LongueurDto> longueurDtos = new ArrayList<>();

      for(Longueur l:longueurs){
         LongueurDto longueurDto = new LongueurDto();

         longueurDto.setId(l.getId());
         longueurDto.setVoieId(l.getVoieId());
         longueurDto.setCotationId(l.getCotationId());
         longueurDto.setHeigth(l.getHeigth());
         longueurDto.setName(l.getName());

         longueurDtos.add(longueurDto);
      }
      return longueurDtos;
   }

   @Override
   public Integer save(LongueurDto longueurDto) {
      Longueur longueur = new Longueur();

      longueur.setId(longueurDto.getId());
      longueur.setVoieId(longueurDto.getVoieId());
      longueur.setCotationId(longueurDto.getCotationId());
      longueur.setHeigth(longueurDto.getHeigth());
      longueur.setName(longueurDto.getName());

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
}
