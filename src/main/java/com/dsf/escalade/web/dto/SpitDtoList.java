package com.dsf.escalade.web.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SpitDtoList {
   private List<SpitDto> spitDtos;

   public void addSpitDto(SpitDto spitDto){
      if(spitDtos == null){
         spitDtos = new ArrayList<>();
      }

      this.spitDtos.add(spitDto);
   }
}
