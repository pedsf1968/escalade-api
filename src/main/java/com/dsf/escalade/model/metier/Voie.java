package com.dsf.escalade.model.metier;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="voie", schema="public")
public class Voie extends Site implements Serializable {
   private Integer secteur;
   private Boolean estEquipee;
   private Integer hauteur;
   private String cotation;

   protected Voie() {}

   public Voie(int id, String nom, Integer secteur, Boolean estEquipee, Integer hauteur, String cotation) {
      super(id, nom, SiteType.VOIE);
      this.secteur = secteur;
      this.estEquipee = estEquipee;
      this.hauteur = hauteur;
      this.cotation = cotation;
   }

   public Integer getSecteur() {
      return secteur;
   }

   public void setSecteur(Integer secteur) {
      this.secteur = secteur;
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

   public String getCotation() {
      return cotation;
   }

   public void setCotation(String cotation) {
      this.cotation = cotation;
   }


   @Override
   public String toString() {
      return String.format(
            "Voie {id=%d, " +
                  "nom='%s', " +
                  "type='%s', " +
                  "secteur='%s', " +
                  "est équipée='%b'," +
                  "hauteur='%d', " +
                  "cotation='%s'," +
                  "aCommentaire='%b', " +
                  "photo='%s', " +
                  "carte='%s'}"
            , id, nom,  type, secteur, estEquipee, hauteur,cotation, aCommentaire, lienPhoto, lienCarte);
   }
}
