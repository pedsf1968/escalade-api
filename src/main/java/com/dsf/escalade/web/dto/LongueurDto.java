package com.dsf.escalade.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LongueurDto {
   private static final int NAME_MIN = 1;
   private static final int NAME_MAX = 50;

   private Integer id;
   @NotNull
   @Size(min = NAME_MIN, max = NAME_MAX, message = "Length should be between : "+ NAME_MIN + " AND " + NAME_MAX + " !")
   private String name;
   private Integer voieId;
   private Integer cotationId;
   private Integer heigth;
}
