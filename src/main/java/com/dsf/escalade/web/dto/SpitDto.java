package com.dsf.escalade.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SpitDto {
   @NotNull
   private Integer topoId;
   @NotNull
   private Integer number;
   @NotNull
   private Integer voieId;
   @NotNull
   private Integer longueurId;
   @NotNull
   private Integer cotationId;
   private Boolean isRelay;
   private String comment;
}
