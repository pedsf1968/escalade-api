package com.dsf.escalade.web.dto;

import lombok.Data;

@Data
public class LongueurCompleteDto implements AutoCloseable{
   private LongueurDto longueur;
   private SpitDtoList spitList;

   @Override
   public void close() throws Exception {
      longueur.close();
      spitList.close();
   }
}
