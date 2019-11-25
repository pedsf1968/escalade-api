package com.dsf.escalade.model.business;

import javax.persistence.*;

@Entity
@Table(name="secteur")
@PrimaryKeyJoinColumn(name = "site_id")
public class Secteur extends Site{
   //@Id
  // private Integer id;
   @Column(name = "topo_id", columnDefinition = "INTEGER(10) NOT NULL")
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
   public String toString() {
      return "Secteur{" +
              "id=" + id +
              ", topo=" + topo +
              ", equipement='" + equipement + '\'' +
              '}';
   }
}
