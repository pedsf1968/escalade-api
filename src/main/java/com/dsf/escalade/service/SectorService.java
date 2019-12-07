package com.dsf.escalade.service;

import com.dsf.escalade.web.dto.SectorDto;

import java.util.List;

public interface SectorService {
   List<SectorDto> findAll();
   List<SectorDto> findByTopoId(Integer id);
   SectorDto getOne(Integer id);
   Integer save(SectorDto sectorDto);
}
