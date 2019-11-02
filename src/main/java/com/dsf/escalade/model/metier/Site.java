package com.dsf.escalade.model.metier;

import com.dsf.escalade.model.global.TypeSite;

import javax.persistence.*;
import java.io.Serializable;


//@MappedSuperclass
@Entity
@Table(name = "site")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Site implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   protected int id;
   @Column(name = "nom", length = 50, nullable = false)
   protected String nom;
   @Column(name = "type")
   @Enumerated(EnumType.STRING)
   protected TypeSite type;
   @Column(name = "a_commentaire")
   protected Boolean aCommentaire;
   @Column(name = "photo_url")
   protected String lienPhoto;
   @Column(name = "map_url")
   protected String lienCarte;

   protected Site() {
   }

   Site(int id, String nom, TypeSite type) {
      this.id = id;
      this.nom = nom;
      this.type = type;
      this.aCommentaire = false;
      this.lienPhoto = null;
      this.lienCarte = null;
   }

   public Site(int id, String nom,  TypeSite type, Boolean aCommentaire, String lienPhoto, String lienCarte) {
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

   public TypeSite getType() {
      return type;
   }

   public void setType(TypeSite type) {
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
            "Site {id=%d, nom='%s',type='%s', a un commentaire='%b', photo='%s', carte='%s'}",
            id, nom,   type, aCommentaire, lienPhoto, lienCarte);
   }

}
