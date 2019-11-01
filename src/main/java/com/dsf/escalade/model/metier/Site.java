package com.dsf.escalade.model.metier;

import javax.persistence.*;


@Entity
public class Site {
   public enum SiteType {TOPO,SECTEUR,VOIE}

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   protected int id;
   @Column(name = "nom", length = 50, nullable = false)
   String nom;
   @Enumerated(EnumType.STRING)
   SiteType type;
   Boolean aCommentaire;
   String lienPhoto;
   String lienCarte;

   protected Site() {
   }

   Site(int id, String nom, SiteType type) {
      this.id = id;
      this.nom = nom;
      this.type = type;
      this.aCommentaire = false;
      this.lienPhoto = null;
      this.lienCarte = null;
   }

   public Site(int id, String nom, SiteType type, Boolean aCommentaire, String lienPhoto, String lienCarte) {
      this.id = id;
      this.nom = nom;
      this.type = type;
      this.aCommentaire = aCommentaire;
      this.lienPhoto = lienPhoto;
      this.lienCarte = lienCarte;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getNom() {
      return nom;
   }

   public void setNom(String nom) {
      this.nom = nom;
   }

   public SiteType getType() {
      return type;
   }

   public void setType(SiteType type) {
      this.type = type;
   }

   public Boolean getaCommentaire() {
      return aCommentaire;
   }

   public void setaCommentaire(Boolean aCommentaire) {
      this.aCommentaire = aCommentaire;
   }

   public String getLienPhoto() {
      return lienPhoto;
   }

   public void setLienPhoto(String lienPhoto) {
      this.lienPhoto = lienPhoto;
   }

   public String getLienCarte() {
      return lienCarte;
   }

   public void setLienCarte(String lienCarte) {
      this.lienCarte = lienCarte;
   }

   @Override
   public String toString() {
      return String.format(
            "Site {id=%d, nom='%s', type='%s', aCommentaire='%b', photo='%s', carte='%s'}",
            id, nom,  type, aCommentaire, lienPhoto, lienCarte);
   }

}
