package com.dsf.escalade.service;

import com.dsf.escalade.web.dto.SecteurDto;

import java.util.List;

public interface SecteurService {
   List<SecteurDto> findAll();
   List<SecteurDto> findByTopoId(Integer id);
   SecteurDto getOne(Integer id);
   Integer save(SecteurDto secteurDto);
}
