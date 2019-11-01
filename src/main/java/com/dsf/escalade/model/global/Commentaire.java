package com.dsf.escalade.model.global;

public class Commentaire {
   private Integer id;
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
