package com.dsf.escalade.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LongueurDto {
   private static final int NAME_MIN = 1;
   private static final int NAME_MAX = 50;
   private static final int HEIGHT_MAX = 50;

   private Integer id;
   @NotNull
   @Size(min = NAME_MIN, max = NAME_MAX)
   private String name;
   private Integer voieId;
   private Integer cotationId;

   @Size(max = HEIGHT_MAX)
   private Integer height;
   private Integer spits;

   public LongueurDto() { super();
   }
}
