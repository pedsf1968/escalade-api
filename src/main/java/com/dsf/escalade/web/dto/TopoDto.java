package com.dsf.escalade.web.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class TopoDto {
   private static final int NAME_MIN = 1;
   private static final int NAME_MAX = 50;
   private static final int REGION_MIN = 1;
   private static final int REGION_MAX = 50;

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

   //Topo attributes
   @NotNull
   @Size(min = REGION_MIN, max = REGION_MAX)
   private String region;
   private Integer addressId;
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   private Date date;
   @NotNull
   private String description;
   private String technic;
   private String access;
   // Don't use username for the manager
   @NotNull
   private String aliasManager;
   // Don't use username for the climber who reserve
   private String aliasClimber;
   private String status;
   private Boolean statusAuto;

   public TopoDto() {
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

   public String getRegion() {
      return region;
   }

   public void setRegion(String region) {
      this.region = region;
   }

   public Integer getAddressId() {
      return addressId;
   }

   public void setAddressId(Integer addressId) {
      this.addressId = addressId;
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

   public String getAliasManager() {
      return aliasManager;
   }

   public void setAliasManager(String aliasManager) {
      this.aliasManager = aliasManager;
   }

   public String getAliasClimber() {
      return aliasClimber;
   }

   public void setAliasClimber(String aliasClimber) {
      this.aliasClimber = aliasClimber;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public Boolean getStatusAuto() {
      return statusAuto;
   }

   public void setStatusAuto(Boolean statusAuto) {
      this.statusAuto = statusAuto;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof TopoDto)) return false;
      TopoDto topoDto = (TopoDto) o;
      return getId().equals(topoDto.getId()) &&
            getName().equals(topoDto.getName()) &&
            Objects.equals(getLongitude(), topoDto.getLongitude()) &&
            Objects.equals(getLatitude(), topoDto.getLatitude()) &&
            getHasComment().equals(topoDto.getHasComment()) &&
            Objects.equals(getPhotoLink(), topoDto.getPhotoLink()) &&
            Objects.equals(getMapLink(), topoDto.getMapLink()) &&
            getRegion().equals(topoDto.getRegion()) &&
            getAddressId().equals(topoDto.getAddressId()) &&
            getDate().equals(topoDto.getDate()) &&
            getDescription().equals(topoDto.getDescription()) &&
            Objects.equals(getTechnic(), topoDto.getTechnic()) &&
            Objects.equals(getAccess(), topoDto.getAccess()) &&
            getAliasManager().equals(topoDto.getAliasManager()) &&
            Objects.equals(getAliasClimber(), topoDto.getAliasClimber()) &&
            getStatus().equals(topoDto.getStatus()) &&
            getStatusAuto().equals(topoDto.getStatusAuto());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getName(), getLongitude(), getLatitude(), getHasComment(), getPhotoLink(), getMapLink(), getRegion(), getAddressId(), getDate(), getDescription(), getTechnic(), getAccess(), getAliasManager(), getAliasClimber(), getStatus(), getStatusAuto());
   }

   @Override
   public String toString() {
      return "TopoDto{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", longitude='" + longitude + '\'' +
            ", latitude='" + latitude + '\'' +
            ", hasComment=" + hasComment +
            ", photoLink='" + photoLink + '\'' +
            ", mapLink='" + mapLink + '\'' +
            ", region='" + region + '\'' +
            ", addressId=" + addressId +
            ", date=" + date +
            ", description='" + description + '\'' +
            ", technic='" + technic + '\'' +
            ", access='" + access + '\'' +
            ", aliasManager='" + aliasManager + '\'' +
            ", aliasClimber='" + aliasClimber + '\'' +
            ", status='" + status + '\'' +
            ", statusAuto=" + statusAuto +
            '}';
   }
}
