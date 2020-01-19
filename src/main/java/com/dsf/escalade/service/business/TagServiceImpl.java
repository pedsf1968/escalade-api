package com.dsf.escalade.service.business;

import com.dsf.escalade.model.global.Tag;
import com.dsf.escalade.model.global.TagList;
import com.dsf.escalade.repository.global.TagListRepository;
import com.dsf.escalade.repository.global.TagRepository;
import com.dsf.escalade.web.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("TagService")
public class TagServiceImpl implements TagService {
   private final TagRepository tagRepository;
   private final TagListRepository tagListRepository;

   @Autowired
   public TagServiceImpl(TagRepository tagRepository, TagListRepository tagListRepository) {
      this.tagRepository = tagRepository;
      this.tagListRepository = tagListRepository;
   }

   @Override
   public TagDto entityToDto(Tag tag) {
      if(tag==null){
         return null;
      }

      TagDto tagDto = new TagDto();
      tagDto.setId(tag.getId());
      tagDto.setName(tag.getName());
      tagDto.setActivated(Boolean.FALSE);
      return tagDto;

   }

   @Override
   public Tag dtoToEntity(TagDto tagDto) {
      return null;
   }

   @Override
   public TagDto getOne(Integer id) {

      Tag tag = tagRepository.getOne(id);

      return entityToDto(tag);
   }


   @Override
   public List<TagDto> findAll() {
      List<TagDto> tagDtos = new ArrayList<>();

      for(Tag tag : tagRepository.findAll()){
         tagDtos.add(entityToDto(tag));
      }

      return tagDtos;
   }

   @Override
   public List<TagDto> findByTopoId(Integer topoId) {
      List<TagDto> tagDtos = findAll();

      List<TagList> tagLists =  tagListRepository.findByTopoId(topoId);

      for(TagList t: tagLists){
         tagDtos.get(t.getTag()-1).setActivated(Boolean.TRUE);
      }

      return tagDtos;
   }

   public Map<Tag,Boolean> tagStatusByTopoId(Integer id){
      Map<Tag,Boolean> tagsStatusByTopoId = new HashMap<>();
      List<TagList> tagLists =  tagListRepository.findByTopoId(id);

      for(Tag t: tagRepository.findAll()){
         tagsStatusByTopoId.put(t,false);
      }

      for(TagList t: tagLists){
         tagsStatusByTopoId.put(tagRepository.getOne(t.getTag()),true);
      }

      return tagsStatusByTopoId;
   }


   @Override
   public Integer update(Integer topoId, List<TagDto> tagDtos) {

      for(TagDto tagDto:tagDtos){
         TagList tagList = new TagList();
         tagList.setTopo(topoId);
         tagList.setTag(tagDto.getId());

         if(Boolean.TRUE.equals(tagDto.getActivated())){
            tagListRepository.save(tagList);
         } else {
            tagListRepository.delete(tagList);
         }
      }

      return topoId;
   }

}
