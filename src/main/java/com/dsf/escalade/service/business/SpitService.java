package com.dsf.escalade.service.business;

import com.dsf.escalade.web.dto.SpitDto;
import com.dsf.escalade.web.dto.SpitDtoList;

public interface SpitService {
   SpitDtoList findByLongueurId(Integer longueurId);
   void save(SpitDto spitDto);
   void saveAll(SpitDtoList spitDtoList);
   void delete(SpitDto spitDto);
   Integer getLastSpitNumber(Integer topoId);
   SpitDto getOne(Integer topoId, Integer number);
}
