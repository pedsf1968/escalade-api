package com.dsf.escalade.service;

import com.dsf.escalade.model.business.Voie;
import com.dsf.escalade.repository.business.VoieRepository;
import com.dsf.escalade.web.dto.VoieDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("VoieService")
public class VoieServiceImpl implements VoieService {
   private final VoieRepository voieRepository;

   public VoieServiceImpl(VoieRepository voieRepository) {
      this.voieRepository = voieRepository;
   }

   @Override
   public VoieDto getOne(Integer id) {
      Voie voie = voieRepository.getOne(id);
      VoieDto voieDto = new VoieDto();

      voieDto.setId(voie.getId());
      voieDto.setName(voie.getName());
      voieDto.setLatitude(voie.getLatitude());
      voieDto.setLongitude(voie.getLongitude());
      voieDto.setParentId(voie.getParentId());
      voieDto.setPhotoLink(voie.getPhotoLink());
      voieDto.setMapLink(voie.getMapLink());
      voieDto.setHasComment(voie.getHasComment());
      voieDto.setCotationId(voie.getCotationId());
      voieDto.setHeigth(voie.getHeigth());
      voieDto.setIsEquipped(voie.getIsEquipped());

      return voieDto;
   }

   @Override
   public List<VoieDto> findByParentId(Integer parentId) {
      List<VoieDto> voieDtos = new ArrayList<>();
      VoieDto voieDto = null;

      for(Voie voie : voieRepository.findByParentId(parentId)){
         voieDto = new VoieDto();

         voieDto.setId(voie.getId());
         voieDto.setName(voie.getName());
         voieDto.setLatitude(voie.getLatitude());
         voieDto.setLongitude(voie.getLongitude());
         voieDto.setParentId(voie.getParentId());
         voieDto.setPhotoLink(voie.getPhotoLink());
         voieDto.setMapLink(voie.getMapLink());
         voieDto.setHasComment(voie.getHasComment());
         voieDto.setCotationId(voie.getCotationId());
         voieDto.setHeigth(voie.getHeigth());
         voieDto.setIsEquipped(voie.getIsEquipped());

         voieDtos.add(voieDto);
      }

      return voieDtos;
   }

   @Override
   public Integer save(VoieDto voieDto) {
      Voie voie = new Voie();

      voie.setId(voieDto.getId());
      voie.setName(voieDto.getName());
      voie.setLatitude(voieDto.getLatitude());
      voie.setLongitude(voieDto.getLongitude());
      voie.setParentId(voieDto.getParentId());
      voie.setPhotoLink(voieDto.getPhotoLink());
      voie.setMapLink(voieDto.getMapLink());
      voie.setHasComment(voieDto.getHasComment());
      voie.setCotationId(voieDto.getCotationId());
      voie.setHeigth(voieDto.getHeigth());
      voie.setIsEquipped(voieDto.getIsEquipped());

      return voieRepository.save(voie).getId();
   }

   @Override
   public Integer delete(VoieDto voieDto) {
     Integer voieId = voieDto.getId();

      if(voieId!=null){
         voieRepository.delete(voieRepository.getOne(voieId));
         return voieId;
      }

      return null;
   }
}
