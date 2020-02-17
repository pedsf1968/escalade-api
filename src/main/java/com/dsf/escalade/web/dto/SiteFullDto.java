package com.dsf.escalade.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class SiteFullDto implements AutoCloseable{
   private SiteDto site;
   private List<SectorFullDto> sectorList;
   private List<VoieFullDto> voieList;
   private List<LongueurFullDto> longueurList;

   @Override
   public void close() throws Exception {
      site.close();

      for (SectorFullDto s: sectorList) {
         s.close();
      }

      for (VoieFullDto v: voieList) {
         v.close();
      }

      for (LongueurFullDto l: longueurList) {
         l.close();
      }
   }
}
