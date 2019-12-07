package com.dsf.escalade.model.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="sector")
@PrimaryKeyJoinColumn(name = "site_id")
public class Sector extends Site{
   @Column(name = "topoId", columnDefinition = "INTEGER(10) NOT NULL")
   private Integer topoId;
   @Column(name = "equipement", columnDefinition = "TEXT")
   private String equipement;

   public Sector() {
      super();
   }

   public Integer getTopoId() {
      return topoId;
   }

   public void setTopoId(Integer topoId) {
      this.topoId = topoId;
   }

   public String getEquipement() {
      return equipement;
   }

   public void setEquipement(String equipement) {
      this.equipement = equipement;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Sector)) return false;
      if (!super.equals(o)) return false;
      Sector sector = (Sector) o;
      return getTopoId().equals(sector.getTopoId()) &&
            getEquipement().equals(sector.getEquipement());
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), getTopoId(), getEquipement());
   }

   @Override
   public String toString() {
      return "Sector{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", topoId=" + topoId +
            ", equipement='" + equipement + '\'' +
            ", hasComment=" + hasComment +
            ", photoLink='" + photoLink + '\'' +
            ", mapLink='" + mapLink + '\'' +
            '}';
   }
}
