package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Cotation;
import com.dsf.escalade.repository.business.CotationRepository;
import com.dsf.escalade.web.dto.CotationDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("CotationService")
public class CotationServiceImpl implements CotationService {
   private final CotationRepository cotationRepository;

   public CotationServiceImpl(CotationRepository cotationRepository) {
      this.cotationRepository = cotationRepository;
   }

   @Override
   public List<CotationDto> findAll() {
      List<CotationDto> cotationDtos = new ArrayList<>();

      for(Cotation cotation:cotationRepository.findAll()){
         CotationDto cotationDto = new CotationDto();
         cotationDto.setId(cotation.getId());
         cotationDto.setFRLevel(cotation.getFRLevel());
         cotationDto.setGBLevel(cotation.getGBLevel());
         cotationDto.setUSLevel(cotation.getUSLevel());
         cotationDtos.add(cotationDto);
      }

      return cotationDtos;
   }
}
