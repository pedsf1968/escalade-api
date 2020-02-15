package com.dsf.escalade.web.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class TopoDto implements AutoCloseable{
   private static final int NAME_MIN = 1;
   private static final int NAME_MAX = 50;
   private static final int REGION_MIN = 1;
   private static final int REGION_MAX = 50;
   private static final String LATITUDE_REGEXP="^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?)|)$";
   private static final String LONGITUDE_REGEXP="^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?)|)$";


   //Site attributes
   private Integer id;
   @NotNull
   @Size(min = NAME_MIN, max = NAME_MAX, message = "Length should be between : "+ NAME_MIN + " AND " + NAME_MAX + " !")
   private String name;
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

   //Topo attributes
   @NotNull
   @NotBlank
   @Size(min = REGION_MIN, max = REGION_MAX, message = "Length should be between : "+ REGION_MIN + " AND " + REGION_MAX + " !")
   private String region;
   private Integer addressId;
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   private Date date;
   @NotNull
   @NotBlank
   private String description;
   private String technic;
   private String access;
   // Don't use username for the climber who reserve
   private String aliasClimber;
   private String status;
   private Boolean statusAuto;
   private Integer cotationMin;
   private Integer cotationMax;
   private Integer nbLane;


   @Override
   public void close() throws Exception {

   }
}
