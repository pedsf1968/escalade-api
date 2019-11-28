package com.dsf.escalade.web.dto;

import com.dsf.escalade.model.business.Site;
import com.dsf.escalade.model.business.Topo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class TopoDto extends Site{
   private static final int NAME_MIN = 1;
   private static final int REGION_MIN = 1;

      @NotNull
   @Size(min = NAME_MIN)
   protected String name;
   private String longitude;
   private String latitude;
   protected Boolean hasComment;
   protected String photoLink;
   protected String mapLink;

   @NotNull
   @Size(min = REGION_MIN)
   private String region;
   private Date date;
   private String description;
   private String technic;
   private String acces;
   private String pseudo;
   private String statut;


   public TopoDto(Site site, Topo topo) {
      this.name = site.getName();
      this.longitude = site.getLongitude();
      this.latitude = site.getLatitude();
      this.hasComment = site.getHasComment();
      this.photoLink = site.getPhotoLink();
      this.mapLink = site.getMapLink();
      this.region = topo.getRegion();
      this.date = topo.getDate();
      this.description = topo.getDescription();
      this.technic = topo.getTechnic();
      this.acces = topo.getAcces();
      this.statut = topo.getStatut().toString();
   }

   @Override
   public String getName() {
      return name;
   }

   @Override
   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String getLongitude() {
      return longitude;
   }

   @Override
   public void setLongitude(String longitude) {
      this.longitude = longitude;
   }

   @Override
   public String getLatitude() {
      return latitude;
   }

   @Override
   public void setLatitude(String latitude) {
      this.latitude = latitude;
   }

   @Override
   public Boolean getHasComment() {
      return hasComment;
   }

   @Override
   public void setHasComment(Boolean hasComment) {
      this.hasComment = hasComment;
   }

   @Override
   public String getPhotoLink() {
      return photoLink;
   }

   @Override
   public void setPhotoLink(String photoLink) {
      this.photoLink = photoLink;
   }

   @Override
   public String getMapLink() {
      return mapLink;
   }

   @Override
   public void setMapLink(String mapLink) {
      this.mapLink = mapLink;
   }

   public String getRegion() {
      return region;
   }

   public void setRegion(String region) {
      this.region = region;
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

   public String getTechnic() {
      return technic;
   }

   public void setTechnic(String technic) {
      this.technic = technic;
   }

   public String getAcces() {
      return acces;
   }

   public void setAcces(String acces) {
      this.acces = acces;
   }

   public String getPseudo() {
      return pseudo;
   }

   public void setPseudo(String pseudo) {
      this.pseudo = pseudo;
   }

   public String getStatut() {
      return statut;
   }

   public void setStatut(String statut) {
      this.statut = statut;
   }
}
