package com.dsf.escalade.service.global;

import com.dsf.escalade.web.dto.CommentDto;

import java.util.List;

public interface CommentService {
   public CommentDto getOne(Integer id);
   public Integer save(CommentDto commentDto);
   public List<CommentDto> getBySiteId(Integer id);
   public List<CommentDto> getByUserId(Integer id);
   Integer delete(CommentDto commentDto);
}
