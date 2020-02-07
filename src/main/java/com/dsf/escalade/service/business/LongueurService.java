package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Longueur;
import com.dsf.escalade.web.dto.LongueurCompleteDto;
import com.dsf.escalade.web.dto.LongueurDto;

import java.util.List;

public interface LongueurService {
   LongueurDto entityToDto(Longueur longueur);
   Longueur dtoToEntity(LongueurDto longueurDto);
   LongueurDto getOne(Integer id);
   List<LongueurDto> findByVoieId(Integer voieId);
   Integer save(LongueurDto longueurDto);
   Integer delete(LongueurDto longueurDto);
   void updateCotation(LongueurDto longueurDto);
   LongueurCompleteDto getFull(Integer longueurId);
}
