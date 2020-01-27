package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TagRepositoryTest {

    @Autowired
    TagRepository tagRepository;

    @Test
    void getOne() {
        Tag tag = new Tag();

        tag.setName("new tag");
        tag = tagRepository.save(tag);

        Tag tagGet = tagRepository.getOne(tag.getId());
        assertEquals(tagGet.getId(), tag.getId());
        assertEquals(tagGet.getName(), tag.getName());
    }
}