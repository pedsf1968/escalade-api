package com.dsf.escalade.model.metier;

import javax.persistence.*;

@Entity
@Table(name="secteur")
@PrimaryKeyJoinColumn(name = "id")
public class Secteur extends Site {
   @Column(name = "topo_id")
   private Integer topo;
   private String longitude;
   private String latitude;
   private String equipement;

   protected Secteur() {
      super();
   }

   public Secteur(int id, String nom, Integer topo, String longitude, String latitude, String equipement) {
      super(id, nom, SiteType.SECTEUR);
      this.topo = topo;
      this.longitude = longitude;
      this.latitude = latitude;
      this.equipement = equipement;
   }

   public Integer getTopo() {
      return topo;
   }

   public void setTopo(Integer topo) {
      this.topo = topo;
   }

   public String getLongitude() {
      return longitude;
   }

   public void setLongitude(String longitude) {
      this.longitude = longitude;
   }

   public String getLatitude() {
      return latitude;
   }

   public void setLatitude(String latitude) {
      this.latitude = latitude;
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
            "Secteur {id=%d, nom='%s', topo='%s', longitude='%s', latitude='%s', a un commentaire='%b', photo='%s', carte='%s', équipement='%s'}",
             id, nom,   topo, longitude, latitude, aCommentaire, lienPhoto, lienCarte, equipement);
   }

}
