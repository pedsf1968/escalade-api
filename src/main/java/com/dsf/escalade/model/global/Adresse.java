package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Adresse implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   private String appartement;
   private String etage;
   private String couloir;
   private String escalier;
   private String entree;
   private String immeuble;
   private String residence;
   private String numero;
   private String voie;
   private String place;
   private String code;
   private String ville;
   private String pays;
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
