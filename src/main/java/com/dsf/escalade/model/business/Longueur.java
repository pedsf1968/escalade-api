package com.dsf.escalade.model.business;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "longueur")
public class Longueur implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;
   @Column(name = "voie_id")
   private Integer voieId;
   @Column(name = "name", columnDefinition = "VARCHAR(50) NOT NULL")
   private String name;
   @Column(name = "cotation_id")
   private Integer cotationId;
   @Column(name = "heigth")
   private Integer heigth;
   public Longueur() { super();
   }

}
