package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.TagList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagListRepository extends JpaRepository <TagList, Integer> {
   List<TagList> findAll();
   @Query("SELECT t FROM TagList t WHERE t.topo = ?1")
   List<TagList> findByTopoId(Integer topo);
}
