package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.TagList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class TagListRepositoryTest {

    @Autowired
    TagListRepository tagListRepository;

    @Test
    void findAll() {
        List<TagList> tags = tagListRepository.findAll();

    }

    @Test
    void findByTopoId() {
    }
}