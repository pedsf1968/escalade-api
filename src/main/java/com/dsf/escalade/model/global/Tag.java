package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tag")
public class Tag implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private Long id;
   @Column(name = "nom", columnDefinition = "VARCHAR(20) NOT NULL")
   private String nom;

   public Tag() {
   }

   public Tag(Long id, String name) {
      this.id = id;
      this.nom = name;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNom() {
      return nom;
   }

   public void setNom(String name) {
      this.nom = name;
   }

   @Override
   public String toString() {
      return "Tag{" +
            "id=" + id +
            ", name='" + nom + '\'' +
            '}';
   }
}
