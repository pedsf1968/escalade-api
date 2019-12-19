package com.dsf.escalade.service.business;

import com.dsf.escalade.web.dto.TopoDto;

import java.util.List;

public interface TopoService {
   TopoDto getOne(Integer id);
   List<TopoDto> findAll();
   List<TopoDto> findAllFiltered(String region, String status);
   List<TopoDto> findByManagerId(Integer id);
   Integer save(TopoDto topoDto);
   Integer delete(TopoDto topoDto);
   List<String> findAllRegion();
}
