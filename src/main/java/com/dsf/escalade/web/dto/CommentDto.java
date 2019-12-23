package com.dsf.escalade.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CommentDto implements Serializable {

   private Integer id;
   private Integer siteId;
   @NotNull
   private String alias;
   private String text;
}
