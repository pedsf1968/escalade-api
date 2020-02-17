package com.dsf.escalade.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class VoieFullDto implements AutoCloseable {
   private VoieDto voie;
   private List<LongueurFullDto> longueurList;

   @Override
   public void close() throws Exception {
      voie.close();
      for (LongueurFullDto l: longueurList) {
         l.close();
      }
   }
}
