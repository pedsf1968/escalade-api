package com.dsf.escalade.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TagDto {
   private static final int TAG_MIN = 1;
   private static final int TAG_MAX = 20;

   private Integer id;
   @NotNull
   @Size(min = TAG_MIN, max = TAG_MAX, message = "Length should be between : "+ TAG_MIN + " AND " + TAG_MAX + " !")
   private String name;
   private Boolean activated;
}
