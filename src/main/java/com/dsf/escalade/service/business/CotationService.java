package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Cotation;
import com.dsf.escalade.web.dto.CotationDto;

import java.util.List;

public interface CotationService {
   // repository override methods
   List<CotationDto> findAll();

   // converters
   CotationDto entityToDto(Cotation cotation);
   Cotation dtoToEntity(CotationDto cotationDto);
}
