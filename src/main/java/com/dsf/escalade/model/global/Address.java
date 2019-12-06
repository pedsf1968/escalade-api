package com.dsf.escalade.model.global;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @NotNull
   @Column(name = "street1", columnDefinition = "VARCHAR(50)")
   private String street1;
   @Column(name = "street2", columnDefinition = "VARCHAR(50)")
   private String street2;
   @NotNull
   @Column(name = "zip_code", columnDefinition = "VARCHAR(6)")
   private String zipCode;
   @NotNull
   @Column(name = "city", columnDefinition = "VARCHAR(50)")
   private String city;
   @NotNull
   @Column(name = "country", columnDefinition = "VARCHAR(50) DEFAULT 'FRANCE'")
   private String country;

   public Address() {
      super();
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
      if (!(o instanceof Address)) return false;
      Address address = (Address) o;
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
