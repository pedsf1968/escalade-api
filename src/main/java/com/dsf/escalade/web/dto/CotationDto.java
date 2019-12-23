package com.dsf.escalade.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CotationDto {
   private Integer id;
   @NotNull
   private String levelFR;
   @NotNull
   private String levelUS;
   @NotNull
   private String levelGB;
}
