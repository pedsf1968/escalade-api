package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Commentaire implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "site_id")
   private Long id;
   @Column(name = "grimpeur_id", columnDefinition = "NOT NULL")
   private Long grimpeur;
   @Column(name = "texte", columnDefinition = "TEXT")
   private String texte;

   public Commentaire() {
   }

   public Commentaire(Long id, Long grimpeur, String texte) {
      this.id = id;
      this.grimpeur = grimpeur;
      this.texte = texte;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getGrimpeur() {
      return grimpeur;
   }

   public void setGrimpeur(Long grimpeur) {
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
