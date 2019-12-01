package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Integer> {
   List<Comment> getBySiteId(Integer id);
   List<Comment> getByUserId(Integer id);
}
