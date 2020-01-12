package com.dsf.escalade.service.business;

import com.dsf.escalade.web.dto.SectorDto;

import java.util.List;

public interface SectorService {
   SectorDto getOne(Integer id);
   List<SectorDto> findAll();
   List<SectorDto> findByTopoId(Integer id);
   Integer save(SectorDto sectorDto);
   Integer delete(SectorDto sectorDto);
   Boolean hasRight(SectorDto sectorDto);
}
