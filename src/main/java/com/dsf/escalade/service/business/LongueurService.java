package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Longueur;
import com.dsf.escalade.web.dto.LongueurDto;
import com.dsf.escalade.web.dto.LongueurFullDto;

import java.util.List;

public interface LongueurService {
   // repository override methods
   LongueurDto getOne(Integer longueurId);
   List<LongueurDto> findByVoieId(Integer voieId);
   Integer save(LongueurDto longueurDto);
   Integer delete(LongueurDto longueurDto);

   // Complete versions
   LongueurFullDto getFull(Integer longueurId);
   Integer saveFull(LongueurFullDto longueurFullDto);

   // converters
   LongueurDto entityToDto(Longueur longueur);
   Longueur dtoToEntity(LongueurDto longueurDto);

   // utilities methods
   void updateCotation(LongueurDto longueurDto);
}
