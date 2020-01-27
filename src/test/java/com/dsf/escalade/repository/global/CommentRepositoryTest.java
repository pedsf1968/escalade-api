package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.Address;
import com.dsf.escalade.model.global.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    void getOne() {
        Comment comment = new Comment();

        comment.setSiteId(2);
        comment.setText("mlkmlkjhmh mkljhmljkhmlkhmlkh mljhmkljhmkjl");
        comment.setUserId(3);

        comment = commentRepository.save(comment);

        Comment commentGet = commentRepository.getOne(comment.getId());
        assertEquals(commentGet.getId(), comment.getId());
        assertEquals(commentGet.getSiteId(), comment.getSiteId());
        assertEquals(commentGet.getText(), comment.getText());
        assertEquals(commentGet.getUserId(), comment.getUserId());
    }

    @Test
    void getBySiteId() {
        Comment comment = new Comment();
        Integer siteId = 3;

        comment.setSiteId(siteId);
        comment.setText("mlkmlkjhmh mkljhmljkhmlkhmlkh mljhmkljhmkjl");
        comment.setUserId(2);

        comment = commentRepository.save(comment);
        List<Comment> comments = commentRepository.getBySiteId(siteId);

        for (Comment commentSaved:comments){
            assertEquals(commentSaved.getSiteId(), comment.getSiteId());
            if(commentSaved.getId()==comment.getId()){
                assertEquals(commentSaved.getText(), comment.getText());
                assertEquals(commentSaved.getUserId(), comment.getUserId());
            }
        }
    }

    @Test
    void getByUserId() {
        Comment comment = new Comment();
        Integer userId = 3;

        comment.setSiteId(2);
        comment.setText("mlkmlkjhmh mkljhmljkhmlkhmlkh mljhmkljhmkjl");
        comment.setUserId(userId);

        comment = commentRepository.save(comment);
        List<Comment> comments = commentRepository.getByUserId(userId);

        for (Comment commentSaved:comments){
            assertEquals(commentSaved.getUserId(), comment.getUserId());

            if(commentSaved.getId()==comment.getId()){
                assertEquals(commentSaved.getText(), comment.getText());
                assertEquals(commentSaved.getSiteId(), comment.getSiteId());
            }
        }
    }
}