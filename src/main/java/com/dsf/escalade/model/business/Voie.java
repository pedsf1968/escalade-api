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
   @Column(name = "is_equipped", columnDefinition = "BOOLEAN DEFAULT FALSE")
   private Boolean isEquipped;
   @Column(name = "heigth", columnDefinition = "INTEGER(3)")
   private Integer heigth;
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

   public Boolean getEquipped() {
      return isEquipped;
   }

   public void setEquipped(Boolean equipped) {
      isEquipped = equipped;
   }

   public Integer getHeigth() {
      return heigth;
   }

   public void setHeigth(Integer heigth) {
      this.heigth = heigth;
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
            isEquipped.equals(voie.isEquipped) &&
            Objects.equals(getHeigth(), voie.getHeigth()) &&
            Objects.equals(getCotationId(), voie.getCotationId());
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), getSecteurId(), isEquipped, getHeigth(), getCotationId());
   }

   @Override
   public String toString() {
      return "Voie{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", secteurId=" + secteurId +
            ", isEquipped=" + isEquipped +
            ", heigth=" + heigth +
            ", cotationId=" + cotationId +
            ", hasComment=" + hasComment +
            ", photoLink='" + photoLink + '\'' +
            ", mapLink='" + mapLink + '\'' +
            '}';
   }
}
