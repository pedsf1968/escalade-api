package com.dsf.escalade.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cotation")
public class Cotation {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", columnDefinition = "INTEGER(2)")
   private Integer id;
   @Column(name = "niveau_fr", columnDefinition = "VARCHAR(3) NOT NULL")
   private String niveauFr;

   public Cotation() {
      super();
   }

   public Cotation(Integer id, String niveauFr) {
      this.id = id;
      this.niveauFr = niveauFr;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getNiveauFr() {
      return niveauFr;
   }

   public void setNiveauFr(String niveauFr) {
      this.niveauFr = niveauFr;
   }

   @Override
   public String toString() {
      return "Cotation{" +
            "id=" + id +
            ", niveauFr='" + niveauFr + '\'' +
            '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Cotation)) return false;
      Cotation cotation = (Cotation) o;
      return getId().equals(cotation.getId()) &&
            getNiveauFr().equals(cotation.getNiveauFr());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getNiveauFr());
   }
}
