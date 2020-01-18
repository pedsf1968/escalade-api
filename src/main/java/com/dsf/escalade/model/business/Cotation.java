package com.dsf.escalade.model.business;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "cotation")
public class Cotation {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;
   @Column(name = "FR", columnDefinition = "VARCHAR(3) NOT NULL")
   private String levelFR;
   @Column(name = "US", columnDefinition = "VARCHAR(5) NOT NULL")
   private String levelUS;
   @Column(name = "GB", columnDefinition = "VARCHAR(2) NOT NULL")
   private String levelGB;

   public Cotation() {
      super();
   }
}
