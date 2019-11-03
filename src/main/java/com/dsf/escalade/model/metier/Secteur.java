package com.dsf.escalade.model.metier;

import javax.persistence.*;

@Entity
@Table(name="secteur")
@PrimaryKeyJoinColumn(name = "id")
public class Secteur extends Site {
   @Column(name = "topo_id", columnDefinition = "INTEGER(10)")
   private Integer topo;
   @Column(name = "equipement", columnDefinition = "TEXT")
   private String equipement;

   protected Secteur() {
      super();
   }

   public Secteur(int id, String nom, Integer topo, String equipement) {
      super(id, nom, SiteType.SECTEUR);
      this.topo = topo;
      this.equipement = equipement;
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
      return String.format(
            "Secteur {id=%d, nom='%s', topo='%s', a un commentaire='%b', photo='%s', carte='%s', équipement='%s'}",
             id, nom,   topo, aCommentaire, lienPhoto, lienCarte, equipement);
   }

}
