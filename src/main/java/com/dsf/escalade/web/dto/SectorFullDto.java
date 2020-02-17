package com.dsf.escalade.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class SectorFullDto implements AutoCloseable{
   private SectorDto sector;
   private List<VoieFullDto> voieList;

   @Override
   public void close() throws Exception {
      for (VoieFullDto v: voieList) {
         v.close();
      }
   }
}
