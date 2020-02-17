package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Voie;
import com.dsf.escalade.web.dto.VoieDto;
import com.dsf.escalade.web.dto.VoieFullDto;

import java.util.List;

public interface VoieService {
   // repository override methods
   VoieDto getOne(Integer id);
   List<VoieDto> findByParentId(Integer id);
   Integer save(VoieDto voieDto);
   Integer delete(VoieDto voieDto);

   // Complete versions
   VoieFullDto getFull(Integer voieId);
   Integer saveFull(VoieFullDto voieFullDto);

   // converters
   VoieDto entityToDto(Voie voie);
   Voie dtoToEntity(VoieDto voieDto);

   // utilities methods
   Boolean hasRight(VoieDto voieDto);
   void updateCotation(VoieDto voieDto);
}
