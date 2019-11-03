package com.dsf.escalade.model.metier;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Longueur implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", columnDefinition = "INTEGER(10)")
   private Integer id;
   @Column(name = "nom", columnDefinition = "VARCHAR(50)")
   private String nom;
   @Column(name = "cotation_id", columnDefinition = "INTEGER(2)")
   private Integer cotation;
   @Column(name = "voie", columnDefinition = "INTEGER(10)")
   private Integer voie;
   @Column(name = "hauteur", columnDefinition = "INTEGER(4)")
   private Integer hauteur;
   @Column(name = "nb_spits", columnDefinition = "INTEGER(3)")
   private Integer nbSpits;

   protected Longueur() {
   }

   public Longueur(Integer id, String nom, Integer cotation, Integer voie, Integer hauteur, Integer nbSpits) {
      this.id = id;
      this.nom = nom;
      this.cotation = cotation;
      this.voie = voie;
      this.hauteur = hauteur;
      this.nbSpits = nbSpits;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getNom() {
      return nom;
   }

   public void setNom(String nom) {
      this.nom = nom;
   }

   public Integer getCotation() {
      return cotation;
   }

   public void setCotation(Integer cotation) {
      this.cotation = cotation;
   }

   public Integer getVoie() {
      return voie;
   }

   public void setVoie(Integer voie) {
      this.voie = voie;
   }

   public Integer getHauteur() {
      return hauteur;
   }

   public void setHauteur(Integer hauteur) {
      this.hauteur = hauteur;
   }

   public Integer getNbSpits() {
      return nbSpits;
   }

   public void setNbSpits(Integer nbSpits) {
      this.nbSpits = nbSpits;
   }

   @Override
   public String toString() {
      return String.format(
            "Longueur {id=%d, " +
                  "nom='%s', " +
                  "cotation=%d, " +
                  "voie='%s', " +
                  "hauteur='%d', " +
                  "nombre de spits='%d'}",
                  id, nom,  cotation, voie, hauteur, nbSpits);
   }


}
