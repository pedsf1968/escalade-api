package com.dsf.escalade.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SiteDto implements AutoCloseable {
   private static final int NAME_MIN = 1;
   private static final int NAME_MAX = 50;
   private static final String LATITUDE_REGEXP="^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?)|)$";
   private static final String LONGITUDE_REGEXP="^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?)|)$";


   private Integer id;
   @NotNull
   @Size(min = NAME_MIN, max = NAME_MAX, message = "Length should be between : "+ NAME_MIN + " AND " + NAME_MAX + " !")
   private String name;
   protected String type;
   // Don't use username for the manager
   @NotNull
   @NotBlank
   private String aliasManager;
   @Pattern(regexp = LONGITUDE_REGEXP, message = "Not a valid longitude (-180,+180) !")
   private String longitude;
   @Pattern(regexp = LATITUDE_REGEXP, message = "Not a valid latitude (-90,+90) !")
   private String latitude;
   private Integer nbComment;
   private String photoLink;
   private String mapLink;

   @Override
   public void close() throws Exception {

   }
}
