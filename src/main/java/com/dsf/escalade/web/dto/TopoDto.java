package com.dsf.escalade.web.dto;

import com.dsf.escalade.model.business.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class TopoDto {
   private static final int NAME_MIN = 1;
   private static final int REGION_MIN = 1;

   private Integer id;
   @NotNull
   @Size(min = NAME_MIN)
   private String name;
   private String longitude;
   private String latitude;
   private Boolean hasComment;
   private String photoLink;
   private String mapLink;

   @NotNull
   @Size(min = REGION_MIN)
   private String region;
   private Date date;
   private String description;
   private String technic;
   private String access;
   private String alias;
   private String status;


   public TopoDto(Topo topo) {
      this.id = topo.getId();
      this.name = topo.getName();
      this.longitude = topo.getLongitude();
      this.latitude = topo.getLatitude();
      this.hasComment = topo.getHasComment();
      this.photoLink = topo.getPhotoLink();
      this.mapLink = topo.getMapLink();
      this.region = topo.getRegion();
      this.date = topo.getDate();
      this.description = topo.getDescription();
      this.technic = topo.getTechnic();
      this.access = topo.getAccess();
      this.status = topo.getStatus().toString();
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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

   public Boolean getHasComment() {
      return hasComment;
   }

   public void setHasComment(Boolean hasComment) {
      this.hasComment = hasComment;
   }

   public String getPhotoLink() {
      return photoLink;
   }

   public void setPhotoLink(String photoLink) {
      this.photoLink = photoLink;
   }

   public String getMapLink() {
      return mapLink;
   }

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
