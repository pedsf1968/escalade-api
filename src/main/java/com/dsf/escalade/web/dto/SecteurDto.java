package com.dsf.escalade.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class SecteurDto {
   private static final int NAME_MIN = 1;
   private static final int NAME_MAX = 50;

   //Site attributes
   private Integer id;
   @NotNull
   @Size(min = NAME_MIN, max = NAME_MAX)
   private String name;
   private String longitude;
   private String latitude;
   private Boolean hasComment;
   private String photoLink;
   private String mapLink;

   //Sector attributes
   private Integer topoId;
   private String equipement;

   public SecteurDto() {
      super();
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

   public Integer getTopoId() {
      return topoId;
   }

   public void setTopoId(Integer topoId) {
      this.topoId = topoId;
   }

   public String getEquipement() {
      return equipement;
   }

   public void setEquipement(String equipement) {
      this.equipement = equipement;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof SecteurDto)) return false;
      SecteurDto that = (SecteurDto) o;
      return getId().equals(that.getId()) &&
            getName().equals(that.getName()) &&
            Objects.equals(getLongitude(), that.getLongitude()) &&
            Objects.equals(getLatitude(), that.getLatitude()) &&
            getHasComment().equals(that.getHasComment()) &&
            Objects.equals(getPhotoLink(), that.getPhotoLink()) &&
            Objects.equals(getMapLink(), that.getMapLink()) &&
            getTopoId().equals(that.getTopoId()) &&
            Objects.equals(getEquipement(), that.getEquipement());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getName(), getLongitude(), getLatitude(), getHasComment(), getPhotoLink(), getMapLink(), getTopoId(), getEquipement());
   }

   @Override
   public String toString() {
      return "SecteurDto{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", longitude='" + longitude + '\'' +
            ", latitude='" + latitude + '\'' +
            ", hasComment=" + hasComment +
            ", photoLink='" + photoLink + '\'' +
            ", mapLink='" + mapLink + '\'' +
            ", topoId=" + topoId +
            ", equipement='" + equipement + '\'' +
            '}';
   }
}
