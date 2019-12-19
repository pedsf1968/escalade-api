package com.dsf.escalade.service.global;

import com.dsf.escalade.model.global.Comment;
import com.dsf.escalade.repository.global.CommentRepository;
import com.dsf.escalade.repository.global.UserRepository;
import com.dsf.escalade.web.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {
   private final CommentRepository commentRepository;
   private final UserRepository userRepository;

   public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
      this.commentRepository = commentRepository;
      this.userRepository = userRepository;
   }

   @Override
   public CommentDto getOne(Integer id) {
      Comment comment = commentRepository.getOne(id);
      CommentDto commentDto = new CommentDto();

      commentDto.setId(comment.getId());
      commentDto.setSiteId(comment.getSiteId());
      commentDto.setAlias(userRepository.getOne(comment.getUserId()).getAlias());
      commentDto.setText(comment.getText());

       return commentDto;
   }

   @Override
   public Integer save(CommentDto commentDto) {
      Comment comment = new Comment();

      comment.setId(commentDto.getId());
      comment.setSiteId(commentDto.getSiteId());
      comment.setUserId(userRepository.findByAlias(commentDto.getAlias()).getId());
      comment.setText(commentDto.getText());

      return commentRepository.save(comment).getId();
   }

   @Override
   public List<CommentDto> getBySiteId(Integer id) {
      List<CommentDto> commentDtoList = new ArrayList<>();
      CommentDto commentDto = null;

      for(Comment comment:commentRepository.getBySiteId(id)){
         commentDto = new CommentDto();
         commentDto.setId(comment.getId());
         commentDto.setSiteId(comment.getSiteId());
         commentDto.setAlias(userRepository.getOne(comment.getUserId()).getAlias());
         commentDto.setText(comment.getText());
         commentDtoList.add(commentDto);
      }

      return commentDtoList;
   }

   @Override
   public List<CommentDto> getByUserId(Integer id) {
      List<CommentDto> commentDtoList = new ArrayList<>();
      CommentDto commentDto = null;

      for(Comment comment:commentRepository.getByUserId(id)){
         commentDto = new CommentDto();
         commentDto.setId(comment.getId());
         commentDto.setSiteId(comment.getSiteId());
         commentDto.setAlias(userRepository.getOne(comment.getUserId()).getAlias());
         commentDto.setText(comment.getText());
         commentDtoList.add(commentDto);
      }

      return commentDtoList;
   }

   @Override
   public Integer delete(CommentDto commentDto) {
      Integer commentId = commentDto.getId();

      if( commentId != null) {
         Comment comment = commentRepository.getOne(commentId);
         commentRepository.delete(comment);
         return commentId;
      }

      return null;
   }
}
