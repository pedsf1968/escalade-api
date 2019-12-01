package com.dsf.escalade.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="secteur")
@PrimaryKeyJoinColumn(name = "site_id")
public class Secteur extends Site{
   @Column(name = "topoId", columnDefinition = "INTEGER(10) NOT NULL")
   private Integer topo;
   @Column(name = "equipement", columnDefinition = "TEXT")
   private String equipement;

   protected Secteur() {
      super();
   }

   public Integer getTopo() {
      return topo;
   }

   public void setTopo(Integer topo) {
      this.topo = topo;
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
      if (!(o instanceof Secteur)) return false;
      if (!super.equals(o)) return false;
      Secteur secteur = (Secteur) o;
      return getTopo().equals(secteur.getTopo()) &&
            getEquipement().equals(secteur.getEquipement());
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), getTopo(), getEquipement());
   }
}
