package com.dsf.escalade.model.metier;

import javax.persistence.*;
import java.io.Serializable;


//@MappedSuperclass
@Entity
@Table(name = "site")
@Inheritance(strategy = InheritanceType.JOINED)
public class Site implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", columnDefinition = "INTEGER(10)")
   protected int id;
   @Column(name = "nom", columnDefinition = "VARCHAR(50) NOT NULL")
   protected String nom;
   @Column(name = "type", columnDefinition = "VARCHAR(7)  NOT NULL DEFAULT 'TOPO'")
   @Enumerated(EnumType.STRING)
   protected SiteType type;
   @Column(name = "longitude", columnDefinition = "VARCHAR(10)")
   private String longitude;
   @Column(name = "latitude", columnDefinition = "VARCHAR(10)")
   private String latitude;
   @Column(name = "a_commentaire", columnDefinition = "BOOLEAN DEFAULT FALSE")
   protected Boolean aCommentaire;
   @Column(name = "photo_url", columnDefinition = "VARCHAR(255)")
   protected String lienPhoto;
   @Column(name = "map_url", columnDefinition = "VARCHAR(255)")
   protected String lienCarte;

   public Site() {
   }

   public Site(int id, String nom, SiteType type) {
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
      return "Site{" +
            "id=" + id +
            ", nom='" + nom + '\'' +
            ", type=" + type +
            ", longitude='" + longitude + '\'' +
            ", latitude='" + latitude + '\'' +
            ", aCommentaire=" + aCommentaire +
            ", lienPhoto='" + lienPhoto + '\'' +
            ", lienCarte='" + lienCarte + '\'' +
            '}';
   }

   public String toStringOld() {
      return String.format(
            "Site {id=%d, nom='%s',type='%s', a un commentaire='%b', photo='%s', carte='%s'}",
            id, nom,   type, aCommentaire, lienPhoto, lienCarte);
   }

}
