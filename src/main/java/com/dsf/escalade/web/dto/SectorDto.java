package com.dsf.escalade.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class SectorDto {
   private static final int NAME_MIN = 1;
   private static final int NAME_MAX = 50;
   private static final String LATITUDE_REGEXP="^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
   private static final String LONGITUDE_REGEXP="^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";

   //Site attributes
   private Integer id;
   @NotNull
   @Size(min = NAME_MIN, max = NAME_MAX)
   private String name;
   @Pattern(regexp = LONGITUDE_REGEXP, message = "Not a valid longitude !")
   private String longitude;
   @Pattern(regexp = LATITUDE_REGEXP, message = "Not a valid latitude !")
   private String latitude;
   private Boolean hasComment;
   private String photoLink;
   private String mapLink;

   //Sector attributes
   private Integer topoId;
   private String equipment;

   public SectorDto() {
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

   public String getEquipment() {
      return equipment;
   }

   public void setEquipment(String equipment) {
      this.equipment = equipment;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof SectorDto)) return false;
      SectorDto that = (SectorDto) o;
      return getId().equals(that.getId()) &&
            getName().equals(that.getName()) &&
            Objects.equals(getLongitude(), that.getLongitude()) &&
            Objects.equals(getLatitude(), that.getLatitude()) &&
            getHasComment().equals(that.getHasComment()) &&
            Objects.equals(getPhotoLink(), that.getPhotoLink()) &&
            Objects.equals(getMapLink(), that.getMapLink()) &&
            getTopoId().equals(that.getTopoId()) &&
            Objects.equals(getEquipment(), that.getEquipment());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getName(), getLongitude(), getLatitude(), getHasComment(), getPhotoLink(), getMapLink(), getTopoId(), getEquipment());
   }

   @Override
   public String toString() {
      return "SectorDto{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", longitude='" + longitude + '\'' +
            ", latitude='" + latitude + '\'' +
            ", hasComment=" + hasComment +
            ", photoLink='" + photoLink + '\'' +
            ", mapLink='" + mapLink + '\'' +
            ", topoId=" + topoId +
            ", equipment='" + equipment + '\'' +
            '}';
   }
}
