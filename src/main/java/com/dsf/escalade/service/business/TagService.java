package com.dsf.escalade.service.business;

import com.dsf.escalade.model.global.Tag;
import com.dsf.escalade.web.dto.TagDto;

import java.util.List;

public interface TagService {
   TagDto entityToDto(Tag tag);
   Tag dtoToEntity(TagDto tagDto);
   TagDto getOne(Integer id);
   List<TagDto> findAll();
   List<TagDto> findByTopoId(Integer id);
   Integer update(Integer topoId, List<TagDto> tagDtos);
}
