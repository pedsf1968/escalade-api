package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Spit;
import com.dsf.escalade.web.dto.SpitDto;
import com.dsf.escalade.web.dto.SpitDtoList;

public interface SpitService {
   SpitDto entityToDto(Spit spit);
   Spit dtoToEntity(SpitDto spitDto);
   SpitDtoList findByLongueurId(Integer longueurId);
   void save(SpitDto spitDto);
   void saveAll(SpitDtoList spitDtoList);
   void delete(SpitDto spitDto);
   Integer getLastSpitNumber(Integer topoId);
   SpitDto getOne(Integer topoId, Integer number);
   Integer getLongueurCotationAverage(Integer topoId, Integer laneId, Integer lengthId);
   Integer getVoieCotationAverage(Integer topoId, Integer laneId);
   Integer getTopoCotationAverage(Integer topoId);
   Integer getCotationIdMaxBySpitPKTopoId(Integer topoId);
   Integer getCotationIdMinBySpitPKTopoId(Integer topoId);
}
