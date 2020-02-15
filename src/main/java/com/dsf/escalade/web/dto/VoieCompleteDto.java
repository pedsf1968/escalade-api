package com.dsf.escalade.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class VoieCompleteDto implements AutoCloseable {
   private VoieDto voie;
   private List<LongueurCompleteDto> longueurList;

   @Override
   public void close() throws Exception {
      voie.close();
      for (LongueurCompleteDto l: longueurList) {
         l.close();
      }
   }
}
