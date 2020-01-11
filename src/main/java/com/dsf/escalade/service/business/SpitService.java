package com.dsf.escalade.service.business;

import com.dsf.escalade.web.dto.SpitDto;

import java.util.List;

public interface SpitService {
   List<SpitDto> findByLongueurId(Integer longueurId);
   void save(SpitDto spitDto);
   void delete(SpitDto spitDto);
   Integer getLastSpitNumber(Integer topoId);
   SpitDto getOne(Integer topoId, Integer number);
}
