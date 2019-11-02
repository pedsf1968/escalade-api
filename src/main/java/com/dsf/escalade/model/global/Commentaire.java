package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Commentaire implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "site_id")
   private Integer id;
   @Column(name = "grimpeur_id")
   private Integer grimpeur;
   private String texte;

   public Commentaire() {
   }

   public Commentaire(Integer id, Integer grimpeur, String texte) {
      this.id = id;
      this.grimpeur = grimpeur;
      this.texte = texte;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getGrimpeur() {
      return grimpeur;
   }

   public void setGrimpeur(Integer grimpeur) {
      this.grimpeur = grimpeur;
   }

   public String getTexte() {
      return texte;
   }

   public void setTexte(String texte) {
      this.texte = texte;
   }

   @Override
   public String toString() {
      return "Commentaire{" +
            "id=" + id +
            ", grimpeur=" + grimpeur +
            ", texte='" + texte + '\'' +
            '}';
   }
}
