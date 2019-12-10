package com.dsf.escalade.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class VoieDto {
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

   //Voie attributes
   private Integer secteurId;
   private Boolean isEquipped;
   private Integer heigth;
   private Integer cotationId;

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

   public Integer getSecteurId() {
      return secteurId;
   }

   public void setSecteurId(Integer secteurId) {
      this.secteurId = secteurId;
   }

   public Boolean getEquipped() {
      return isEquipped;
   }

   public void setEquipped(Boolean equipped) {
      isEquipped = equipped;
   }

   public Integer getHeigth() {
      return heigth;
   }

   public void setHeigth(Integer heigth) {
      this.heigth = heigth;
   }

   public Integer getCotationId() {
      return cotationId;
   }

   public void setCotationId(Integer cotationId) {
      this.cotationId = cotationId;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof VoieDto)) return false;
      VoieDto voieDto = (VoieDto) o;
      return getId().equals(voieDto.getId()) &&
            getName().equals(voieDto.getName()) &&
            Objects.equals(getLongitude(), voieDto.getLongitude()) &&
            Objects.equals(getLatitude(), voieDto.getLatitude()) &&
            getHasComment().equals(voieDto.getHasComment()) &&
            Objects.equals(getPhotoLink(), voieDto.getPhotoLink()) &&
            Objects.equals(getMapLink(), voieDto.getMapLink()) &&
            getSecteurId().equals(voieDto.getSecteurId()) &&
            isEquipped.equals(voieDto.isEquipped) &&
            Objects.equals(getHeigth(), voieDto.getHeigth()) &&
            Objects.equals(getCotationId(), voieDto.getCotationId());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getName(), getLongitude(), getLatitude(), getHasComment(), getPhotoLink(), getMapLink(), getSecteurId(), isEquipped, getHeigth(), getCotationId());
   }

   @Override
   public String toString() {
      return "VoieDto{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", secteurId=" + secteurId +
            ", longitude='" + longitude + '\'' +
            ", latitude='" + latitude + '\'' +
            ", hasComment=" + hasComment +
            ", photoLink='" + photoLink + '\'' +
            ", mapLink='" + mapLink + '\'' +
            ", isEquipped=" + isEquipped +
            ", heigth=" + heigth +
            ", cotationId=" + cotationId +
            '}';
   }
}
