package com.dsf.escalade.service;

import com.dsf.escalade.web.dto.TopoDto;

import java.util.List;

public interface TopoService {

   List<TopoDto> findAll();
   List<TopoDto> findByManagerId(Integer id);
   TopoDto getOne(Integer id);
   Integer save(TopoDto topoDto);
   Integer delete(TopoDto topoDto);
}
