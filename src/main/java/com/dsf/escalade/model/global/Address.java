package com.dsf.escalade.model.global;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
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
}
