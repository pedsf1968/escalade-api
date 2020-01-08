package com.dsf.escalade.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Data
public class AddressDto  implements Serializable {
   private static final int STREET_MAX = 50;
   private static final int ZIP_MIN = 5;
   private static final int ZIP_MAX = 6;
   private static final int CITY_MAX = 50;
   private static final int COUNTRY_MAX = 50;

   private Integer id;
   @NotNull
   @Size(max = STREET_MAX, message = "Length should less than : "+ STREET_MAX + " !")
   private String street1;
   @Size(max = STREET_MAX, message = "Length should less than : "+ STREET_MAX + " !")
   private String street2;
   @NotNull
   @Size(min = ZIP_MIN, max = ZIP_MAX, message = "Length should be between : "+ ZIP_MIN + " AND " + ZIP_MAX + " !")
   private String zipCode;
   @NotNull
   @Size(max = CITY_MAX, message = "Length should less than : "+ CITY_MAX + " !")
   private String city;
   @Size(max = COUNTRY_MAX, message = "Length should less than : "+ COUNTRY_MAX + " !")
   private String country;
}
