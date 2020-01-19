package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Cotation;
import com.dsf.escalade.repository.business.CotationRepository;
import com.dsf.escalade.web.dto.CotationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("CotationService")
public class CotationServiceImpl implements CotationService {
   private final CotationRepository cotationRepository;

   @Autowired
   public CotationServiceImpl(CotationRepository cotationRepository) {
      this.cotationRepository = cotationRepository;
   }

   @Override
   public CotationDto entityToDto(Cotation cotation) {
      if(cotation==null){
         return null;
      }

      CotationDto cotationDto = new CotationDto();

      cotationDto.setId(cotation.getId());
      cotationDto.setLevelFR(cotation.getLevelFR());
      cotationDto.setLevelGB(cotation.getLevelGB());
      cotationDto.setLevelUS(cotation.getLevelUS());

      return cotationDto;
   }

   @Override
   public Cotation dtoToEntity(CotationDto cotationDto) {
      if(cotationDto==null){
         return null;
      }

      Cotation cotation = new Cotation();

      cotation.setId(cotationDto.getId());
      cotation.setLevelFR(cotationDto.getLevelFR());
      cotation.setLevelGB(cotationDto.getLevelGB());
      cotation.setLevelUS(cotationDto.getLevelUS());

      return cotation;
   }

   @Override
   public List<CotationDto> findAll() {
      List<CotationDto> cotationDtos = new ArrayList<>();

      for(Cotation cotation:cotationRepository.findAll()){
         cotationDtos.add(entityToDto(cotation));
      }

      return cotationDtos;
   }
}
