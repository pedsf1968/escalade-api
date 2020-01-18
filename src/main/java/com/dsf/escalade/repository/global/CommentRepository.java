package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Integer> {
   Comment getOne(Integer CommentId);
   List<Comment> getBySiteId(Integer siteId);
   List<Comment> getByUserId(Integer userId);
}
