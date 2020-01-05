package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.SpitPK;
import com.dsf.escalade.web.dto.SpitDto;

import java.util.List;

public interface SpitService {
   SpitDto getOne(SpitPK spitPK);
   List<SpitDto> findByLongueurId(Integer longueurId);
   void save(SpitDto spitDto);
   void delete(SpitDto spitDto);
}
