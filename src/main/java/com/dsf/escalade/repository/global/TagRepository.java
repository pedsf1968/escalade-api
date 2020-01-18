package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
   Tag getOne(Integer tagId);
}
