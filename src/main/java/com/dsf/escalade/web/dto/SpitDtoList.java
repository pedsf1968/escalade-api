package com.dsf.escalade.web.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SpitDtoList implements AutoCloseable {
   private List<SpitDto> spitDtos;

   public void addSpitDto(SpitDto spitDto){
      if(spitDtos == null){
         spitDtos = new ArrayList<>();
      }

      this.spitDtos.add(spitDto);
   }

   @Override
   public void close() throws Exception {
      for (SpitDto s: spitDtos) {
         s.close();
      }
   }
}
