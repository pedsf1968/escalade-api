package com.dsf.escalade.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class TopoCompleteDto implements AutoCloseable{
   private TopoDto topo;
   private List<SectorCompleteDto> sectorList;
   private List<VoieCompleteDto> voieList;

   @Override
   public void close() throws Exception {
      for (SectorCompleteDto s: sectorList) {
         s.close();
      }

      for(VoieCompleteDto v: voieList){
         v.close();
      }
   }
}
