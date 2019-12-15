package com.dsf.escalade.service;

import com.dsf.escalade.web.dto.TagDto;

import java.util.List;

public interface TagService {
   TagDto getOne(Integer id);
   List<TagDto> findAll();
   List<TagDto> findByTopoId(Integer id);
   Integer update(Integer topoId, List<TagDto> tagDtos);
}
