package com.dsf.escalade.service;

import com.dsf.escalade.web.dto.VoieDto;

import java.util.List;

public interface VoieService {
   VoieDto getOne(Integer id);
   List<VoieDto> findByParentId(Integer id);
   Integer save(VoieDto voieDto);
   Integer delete(VoieDto voieDto);
}
