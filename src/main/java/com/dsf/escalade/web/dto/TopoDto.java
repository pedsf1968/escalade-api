package com.dsf.escalade.web.dto;

import com.dsf.escalade.model.business.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class TopoDto extends Site{
   private static final int NAME_MIN = 1;
   private static final int REGION_MIN = 1;

   protected Long id;
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
   private String access;
   private String alias;
   private String status;


   public TopoDto(Site site, Topo topo) {
      this.id = site.getId();
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
      this.access = topo.getAccess();
      this.status = topo.getStatus().toString();
   }

   @Override
   public Long getId() {
      return id;
   }

   @Override
   public void setId(Long id) {
      this.id = id;
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

   public String getAccess() {
      return access;
   }

   public void setAccess(String access) {
      this.access = access;
   }

   public String getAlias() {
      return alias;
   }

   public void setAlias(String alias) {
      this.alias = alias;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}
