package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Topo;
import com.dsf.escalade.web.dto.TopoCompleteDto;
import com.dsf.escalade.web.dto.TopoDto;

import java.util.List;

public interface TopoService {
   TopoDto entityToDto(Topo topo);
   Topo dtoToEntity(TopoDto topoDto);
   TopoDto getOne(Integer id);
   List<TopoDto> findAll();
   List<TopoDto> findByManagerId(Integer id);
   List<TopoDto> findByClimberId(Integer id);
   Integer save(TopoDto topoDto);
   Integer delete(TopoDto topoDto);
   List<String> findAllRegion();
   List<String> findAllAlias();
   List<TopoDto> findAllFiltered(TopoDto filter);
   void updateCotationRange(Integer topoId);
   Integer increaseLaneCounter(Integer topoId);
   Integer decreaseLaneCounter(Integer topoId);
   Boolean hasRight(TopoDto topoDto);
   TopoCompleteDto getFull(Integer topoId);
}
