package com.dsf.escalade.model.business;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
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
   @Column(name = "spits", columnDefinition = "INTEGER(3)")
   private Integer spits;

   protected Longueur() {
      super();
   }

      public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getCotationId() {
      return cotationId;
   }

   public void setCotationId(Integer cotationId) {
      this.cotationId = cotationId;
   }

   public Integer getVoieId() {
      return voieId;
   }

   public void setVoieId(Integer voieId) {
      this.voieId = voieId;
   }

   public Integer getHeigth() {
      return heigth;
   }

   public void setHeigth(Integer heigth) {
      this.heigth = heigth;
   }

   public Integer getSpits() {
      return spits;
   }

   public void setSpits(Integer spits) {
      this.spits = spits;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Longueur)) return false;
      Longueur longueur = (Longueur) o;
      return getId().equals(longueur.getId()) &&
            getName().equals(longueur.getName()) &&
            Objects.equals(getCotationId(), longueur.getCotationId()) &&
            getVoieId().equals(longueur.getVoieId()) &&
            Objects.equals(getHeigth(), longueur.getHeigth()) &&
            Objects.equals(getSpits(), longueur.getSpits());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getName(), getCotationId(), getVoieId(), getHeigth(), getSpits());
   }

   @Override
   public String toString() {
      return "Longueur{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", cotationId=" + cotationId +
            ", voieId=" + voieId +
            ", heigth=" + heigth +
            ", spits=" + spits +
            '}';
   }
}
