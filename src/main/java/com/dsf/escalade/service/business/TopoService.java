package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Topo;
import com.dsf.escalade.web.dto.TopoDto;
import com.dsf.escalade.web.dto.TopoFullDto;

import java.util.List;

public interface TopoService {
   // repository override methods
   TopoDto getOne(Integer topoId);
   List<TopoDto> findAll();
   List<TopoDto> findByManagerId(Integer managerId);
   List<TopoDto> findByClimberId(Integer climberId);
   Integer save(TopoDto topoDto);
   Integer delete(TopoDto topoDto);

   // Complete versions
   TopoFullDto getFull(Integer topoId);
   Integer saveFull(TopoFullDto topoFullDto);

   // converters
   TopoDto entityToDto(Topo topo);
   Topo dtoToEntity(TopoDto topoDto);

   // utilities methods
   List<String> findAllRegion();
   List<String> findAllAlias();
   List<TopoDto> findAllFiltered(TopoDto filter);
   void updateCotationRange(Integer topoId);
   Integer increaseLaneCounter(Integer topoId);
   Integer decreaseLaneCounter(Integer topoId);
   Boolean hasRight(TopoDto topoDto);


}
