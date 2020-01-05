package com.dsf.escalade.model.business;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "longueur")
public class Longueur implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", columnDefinition = "INTEGER(10)")
   private Integer id;
   @Column(name = "name", columnDefinition = "VARCHAR(50) NOT NULL")
   private String name;
   @Column(name = "cotation_id", columnDefinition = "INTEGER(2) NOT NULL")
   private Integer cotationId;
   @Column(name = "voie_id", columnDefinition = "INTEGER(10) NOT NULL")
   private Integer voieId;
   @Column(name = "heigth", columnDefinition = "INTEGER(4)")
   private Integer heigth;

   public Longueur() { super();   }
}
