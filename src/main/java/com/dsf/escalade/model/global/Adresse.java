package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Adresse implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", columnDefinition = "INTEGER(10)")
   private Integer id;
   @Column(name = "appartement", columnDefinition = "VARCHAR(5)")
   private String appartement;
   @Column(name = "etage", columnDefinition = "VARCHAR(3)")
   private String etage;
   @Column(name = "couloir", columnDefinition = "VARCHAR(5)")
   private String couloir;
   @Column(name = "escalier", columnDefinition = "VARCHAR(5)")
   private String escalier;
   @Column(name = "entree", columnDefinition = "VARCHAR(5)")
   private String entree;
   @Column(name = "immeuble", columnDefinition = "VARCHAR(10)")
   private String immeuble;
   @Column(name = "residence", columnDefinition = "VARCHAR(20)")
   private String residence;
   @Column(name = "numero", columnDefinition = "VARCHAR(5)")
   private String numero;
   @Column(name = "voie", columnDefinition = "VARCHAR(50)")
   private String voie;
   @Column(name = "place", columnDefinition = "VARCHAR(50)")
   private String place;
   @Column(name = "code", columnDefinition = "VARCHAR(6)")
   private String code;
   @Column(name = "ville", columnDefinition = "VARCHAR(50)")
   private String ville;
   @Column(name = "pays", columnDefinition = "VARCHAR(50) DEFAULT 'France'")
   private String pays;
   @Column(name = "commentaire", columnDefinition = "TEXT")
   private String commentaire;

   public Adresse() {
   }

   public Adresse(Integer id, String appartement, String etage, String couloir, String escalier, String entree, String immeuble, String residence, String numero, String voie, String place, String code, String ville, String pays, String commentaire) {
      this.id = id;
      this.appartement = appartement;
      this.etage = etage;
      this.couloir = couloir;
      this.escalier = escalier;
      this.entree = entree;
      this.immeuble = immeuble;
      this.residence = residence;
      this.numero = numero;
      this.voie = voie;
      this.place = place;
      this.code = code;
      this.ville = ville;
      this.pays = pays;
      this.commentaire = commentaire;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getAppartement() {
      return appartement;
   }

   public void setAppartement(String appartement) {
      this.appartement = appartement;
   }

   public String getEtage() {
      return etage;
   }

   public void setEtage(String etage) {
      this.etage = etage;
   }

   public String getCouloir() {
      return couloir;
   }

   public void setCouloir(String couloir) {
      this.couloir = couloir;
   }

   public String getEscalier() {
      return escalier;
   }

   public void setEscalier(String escalier) {
      this.escalier = escalier;
   }

   public String getEntree() {
      return entree;
   }

   public void setEntree(String entree) {
      this.entree = entree;
   }

   public String getImmeuble() {
      return immeuble;
   }

   public void setImmeuble(String immeuble) {
      this.immeuble = immeuble;
   }

   public String getResidence() {
      return residence;
   }

   public void setResidence(String residence) {
      this.residence = residence;
   }

   public String getNumero() {
      return numero;
   }

   public void setNumero(String numero) {
      this.numero = numero;
   }

   public String getVoie() {
      return voie;
   }

   public void setVoie(String voie) {
      this.voie = voie;
   }

   public String getPlace() {
      return place;
   }

   public void setPlace(String place) {
      this.place = place;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getVille() {
      return ville;
   }

   public void setVille(String ville) {
      this.ville = ville;
   }

   public String getPays() {
      return pays;
   }

   public void setPays(String pays) {
      this.pays = pays;
   }

   public String getCommentaire() {
      return commentaire;
   }

   public void setCommentaire(String commentaire) {
      this.commentaire = commentaire;
   }


   @Override
   public String toString() {
      return String.format(
            "Site {id=%d, " +
                  "etage='%s'," +
                  "couloir='%s'," +
                  "escalier='%s'," +
                  "entrée='%s'," +
                  "immeuble='%s'," +
                  "résidence='%s'," +
                  "numéro='%s'," +
                  "voie='%s'," +
                  "place='%s'," +
                  "code='%s'," +
                  "ville='%s'," +
                  "pays='%s'," +
                  "commentaire='%s'",
             id, etage,couloir, escalier, entree,immeuble,residence,numero,voie,place,code,ville,pays,commentaire);
   }

}
