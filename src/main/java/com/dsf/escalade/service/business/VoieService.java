package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Voie;
import com.dsf.escalade.web.dto.VoieCompleteDto;
import com.dsf.escalade.web.dto.VoieDto;

import java.util.List;

public interface VoieService {
   VoieDto entityToDto(Voie voie);
   Voie dtoToEntity(VoieDto voieDto);
   VoieDto getOne(Integer id);
   List<VoieDto> findByParentId(Integer id);
   Integer save(VoieDto voieDto);
   Integer delete(VoieDto voieDto);
   Boolean hasRight(VoieDto voieDto);
   void updateCotation(VoieDto voieDto);
   VoieCompleteDto getFull(Integer voieId);
}
