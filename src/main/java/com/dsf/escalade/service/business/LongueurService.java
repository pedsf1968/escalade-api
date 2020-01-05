package com.dsf.escalade.service.business;

import com.dsf.escalade.web.dto.LongueurDto;

import java.util.List;

public interface LongueurService {
   LongueurDto getOne(Integer id);
   List<LongueurDto> findByVoieId(Integer id);
   Integer save(LongueurDto longueurDto);
   Integer delete(LongueurDto longueurDto);
}
