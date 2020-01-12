package com.dsf.escalade.web.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SpitDtoList {
   private List<SpitDto> spitDtos = new ArrayList<>();

   public void addSpitDto(SpitDto spitDto){
      this.spitDtos.add(spitDto);
   }

}
