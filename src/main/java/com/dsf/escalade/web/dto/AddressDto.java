package com.dsf.escalade.web.dto;

import com.dsf.escalade.model.global.Address;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class AddressDto  implements Serializable {
   private final int STREET_MAX = 50;
   private final int ZIP_MIN = 5;
   private final int ZIP_MAX = 6;
   private final int CITY_MAX = 50;
   private final int COUNTRY_MAX = 50;

   private Integer id;
   @Size(max = STREET_MAX)
   private String street1;
   @Size(max = STREET_MAX)
   private String street2;
   @Size(min=ZIP_MIN, max = ZIP_MAX)
   private String zipCode;
   @Size(max = CITY_MAX)
   private String city;
   @Size(max = COUNTRY_MAX)
   private String country;

   public AddressDto() {
      super();
   }

   public AddressDto(String street1, String street2, String zipCode, String city, String country) {
      this.street1 = street1;
      this.street2 = street2;
      this.zipCode = zipCode;
      this.city = city;
      this.country = country;
   }

   public AddressDto(Address address) {
      this.id = address.getId();
      this.street1 = address.getStreet1();
      this.street2 = address.getStreet2();
      this.zipCode = address.getZipCode();
      this.city = address.getCity();
      this.country = address.getCountry();
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getStreet1() {
      return street1;
   }

   public void setStreet1(String street1) {
      this.street1 = street1;
   }

   public String getStreet2() {
      return street2;
   }

   public void setStreet2(String street2) {
      this.street2 = street2;
   }

   public String getZipCode() {
      return zipCode;
   }

   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof AddressDto)) return false;
      AddressDto address = (AddressDto) o;
      return getId().equals(address.getId()) &&
            getStreet1().equals(address.getStreet1()) &&
            Objects.equals(getStreet2(), address.getStreet2()) &&
            getZipCode().equals(address.getZipCode()) &&
            getCity().equals(address.getCity()) &&
            getCountry().equals(address.getCountry());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getStreet1(), getStreet2(), getZipCode(), getCity(), getCountry());
   }

   @Override
   public String toString() {
      final StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Address [")
            .append("id=").append(id)
            .append(", street=").append(street1).append(street2)
            .append(", zip code=").append(zipCode)
            .append(", email=").append(city)
            .append(", country=").append(country)
            .append("]");

      return stringBuilder.toString();
   }
}
