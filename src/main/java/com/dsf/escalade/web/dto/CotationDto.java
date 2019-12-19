package com.dsf.escalade.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CotationDto {
   private Integer id;
   @NotNull
   private String FRLevel;
   @NotNull
   private String USLevel;
   @NotNull
   private String GBLevel;

   public CotationDto() {
   }
}
