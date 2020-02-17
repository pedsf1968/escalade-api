package com.dsf.escalade.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class TopoFullDto implements AutoCloseable{
   private TopoDto topo;
   private List<SectorFullDto> sectorList;
   private List<VoieFullDto> voieList;

   @Override
   public void close() throws Exception {
      for (SectorFullDto s: sectorList) {
         s.close();
      }

      for(VoieFullDto v: voieList){
         v.close();
      }
   }
}
