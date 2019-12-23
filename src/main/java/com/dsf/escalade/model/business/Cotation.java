package com.dsf.escalade.model.business;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cotation")
public class Cotation {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", columnDefinition = "INTEGER(2)")
   private Integer id;
   @Column(name = "FR", columnDefinition = "VARCHAR(3) NOT NULL")
   private String FRLevel;
   @Column(name = "US", columnDefinition = "VARCHAR(5) NOT NULL")
   private String USLevel;
   @Column(name = "GB", columnDefinition = "VARCHAR(2) NOT NULL")
   private String GBLevel;

   public Cotation() {
      super();
   }
}
