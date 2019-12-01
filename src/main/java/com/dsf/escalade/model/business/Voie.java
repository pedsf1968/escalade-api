package com.dsf.escalade.model.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "voie")
@PrimaryKeyJoinColumn(name = "site_id")
public class Voie extends Site {
   @Column(name = "secteur_id", columnDefinition = "INTEGER(10) NOT NULL")
   private Integer secteurId;
   @Column(name = "est_equipee", columnDefinition = "BOOLEAN DEFAULT FALSE")
   private Boolean estEquipee;
   @Column(name = "hauteur", columnDefinition = "INTEGER(3)")
   private Integer hauteur;
   @Column(name = "cotation_id", columnDefinition = "VARCHAR(2)")
   private Integer cotationId;

   protected Voie() {
      super();
   }

   public Integer getSecteurId() {
      return secteurId;
   }

   public void setSecteurId(Integer secteurId) {
      this.secteurId = secteurId;
   }

   public Boolean getEstEquipee() {
      return estEquipee;
   }

   public void setEstEquipee(Boolean estEquipee) {
      this.estEquipee = estEquipee;
   }

   public Integer getHauteur() {
      return hauteur;
   }

   public void setHauteur(Integer hauteur) {
      this.hauteur = hauteur;
   }

   public Integer getCotationId() {
      return cotationId;
   }

   public void setCotationId(Integer cotationId) {
      this.cotationId = cotationId;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Voie)) return false;
      if (!super.equals(o)) return false;
      Voie voie = (Voie) o;
      return getSecteurId().equals(voie.getSecteurId()) &&
            getEstEquipee().equals(voie.getEstEquipee()) &&
            getHauteur().equals(voie.getHauteur()) &&
            getCotationId().equals(voie.getCotationId());
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), getSecteurId(), getEstEquipee(), getHauteur(), getCotationId());
   }

   @Override
   public String toString() {
      return "Voie{" +
            ", id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", secteurId=" + secteurId +
            ", estEquipee=" + estEquipee +
            ", hauteur=" + hauteur +
            ", cotationId=" + cotationId +
            ", hasComment=" + hasComment +
            ", photoLink='" + photoLink + '\'' +
            ", mapLink='" + mapLink + '\'' +
            '}';
   }
}
