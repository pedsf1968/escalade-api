package com.dsf.escalade.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
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

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getFRLevel() {
      return FRLevel;
   }

   public void setFRLevel(String FRLevel) {
      this.FRLevel = FRLevel;
   }

   public String getUSLevel() {
      return USLevel;
   }

   public void setUSLevel(String USLevel) {
      this.USLevel = USLevel;
   }

   public String getGBLevel() {
      return GBLevel;
   }

   public void setGBLevel(String GBLevel) {
      this.GBLevel = GBLevel;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Cotation)) return false;
      Cotation cotation = (Cotation) o;
      return getId().equals(cotation.getId()) &&
            getFRLevel().equals(cotation.getFRLevel()) &&
            Objects.equals(getUSLevel(), cotation.getUSLevel()) &&
            Objects.equals(getGBLevel(), cotation.getGBLevel());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getFRLevel(), getUSLevel(), getGBLevel());
   }

   @Override
   public String toString() {
      return "Cotation{" +
            "id=" + id +
            ", FRLevel='" + FRLevel + '\'' +
            ", USLevel='" + USLevel + '\'' +
            ", GBLevel='" + GBLevel + '\'' +
            '}';
   }
}
