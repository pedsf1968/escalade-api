package com.dsf.escalade.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class SectorCompleteDto implements AutoCloseable{
   private SectorDto sector;
   private List<VoieCompleteDto> voieList;

   @Override
   public void close() throws Exception {
      for (VoieCompleteDto v: voieList) {
         v.close();
      }
   }
}
