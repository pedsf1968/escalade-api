package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Sector;
import com.dsf.escalade.web.dto.SectorDto;
import com.dsf.escalade.web.dto.SectorFullDto;

import java.util.List;

public interface SectorService {
   // repository override methods
   SectorDto getOne(Integer id);
   List<SectorDto> findAll();
   List<SectorDto> findByTopoId(Integer id);
   Integer save(SectorDto sectorDto);
   Integer delete(SectorDto sectorDto);

   // Complete versions
   SectorFullDto getFull(Integer voieId);
   Integer saveFull(SectorFullDto sectorFullDto);

   // converters
   SectorDto entityToDto(Sector sector);
   Sector dtoToEntity(SectorDto sectorDto);

   // utilities methods
   Boolean hasRight(SectorDto sectorDto);

}
