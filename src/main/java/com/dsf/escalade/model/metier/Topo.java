package com.dsf.escalade.model.metier;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="topo", schema="public")
public class Topo extends Site implements Serializable {
   private String region;
   private Integer adresse;
   private Date date;
   private String description;
   private String technique;
   private String acces;
   private String longitude;
   private String latitude;
   private Integer promoteur;
   private Integer grimpeur;
   private String statut;
   private Boolean statutAuto;

   protected Topo() {}

   public Topo(int id, String nom, String region, Integer adresse, Date date, String description, String technique, String acces, String longitude, String latitude, Integer promoteur, Integer grimpeur, String statut, Boolean statutAuto) {
      super(id, nom, SiteType.TOPO);
      this.region = region;
      this.adresse = adresse;
      this.date = date;
      this.description = description;
      this.technique = technique;
      this.acces = acces;
      this.longitude = longitude;
      this.latitude = latitude;
      this.promoteur = promoteur;
      this.grimpeur = grimpeur;
      this.statut = statut;
      this.statutAuto = statutAuto;
   }

   public String getRegion() {
      return region;
   }

   public void setRegion(String region) {
      this.region = region;
   }

   public Integer getAdresse() {
      return adresse;
   }

   public void setAdresse(Integer adresse) {
      this.adresse = adresse;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getTechnique() {
      return technique;
   }

   public void setTechnique(String technique) {
      this.technique = technique;
   }

   public String getAcces() {
      return acces;
   }

   public void setAcces(String acces) {
      this.acces = acces;
   }

   public String getLongitude() {
      return longitude;
   }

   public void setLongitude(String longitude) {
      this.longitude = longitude;
   }

   public String getLatitude() {
      return latitude;
   }

   public void setLatitude(String latitude) {
      this.latitude = latitude;
   }

   public Integer getPromoteur() {
      return promoteur;
   }

   public void setPromoteur(Integer promoteur) {
      this.promoteur = promoteur;
   }

   public Integer getGrimpeur() {
      return grimpeur;
   }

   public void setGrimpeur(Integer grimpeur) {
      this.grimpeur = grimpeur;
   }

   public String getStatut() {
      return statut;
   }

   public void setStatut(String statut) {
      this.statut = statut;
   }

   public Boolean getStatutAuto() {
      return statutAuto;
   }

   public void setStatutAuto(Boolean statutAuto) {
      this.statutAuto = statutAuto;
   }

   @Override
   public String toString() {
      return String.format(
            "Topo {" +
                  "id=%d, " +
                  "nom='%s', " +
                  "type='%s', " +
                  "region='%s', " +
                  "adresse='%s'," +
                  "date='%td/%tm/%ty', " +
                  "description='%s', " +
                  "technique='%s'," +
                  "accès='%s', " +
                  "longitude='%s', " +
                  "latitude='%s', " +
                  "promoteur='%s'," +
                  "grimpeur='%s'," +
                  "statut='%s'," +
                  "auto='%b', " +
                  "aCommentaire='%b', " +
                  "photo='%s', " +
                  "carte='%s'}",
            id, nom,  type, region, adresse,
            date, description, technique, acces, longitude,
            latitude, promoteur, grimpeur,statut,statutAuto,
            aCommentaire, lienPhoto, lienCarte);
   }


}
