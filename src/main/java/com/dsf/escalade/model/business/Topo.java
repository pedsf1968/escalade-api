package com.dsf.escalade.model.business;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "topo")
@PrimaryKeyJoinColumn(name = "site_id")
public class Topo extends Site{
   @Column(name = "region", columnDefinition = "VARCHAR(50)")
   private String region;
   @Column(name = "address_id", columnDefinition = "INTEGER(10)")
   private Integer addressId;
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   @Column(name = "date", columnDefinition = "DATE DEFAULT NOW()")
   private Date date;
   @Column(name = "description", columnDefinition = "TEXT")
   private String description;
   @Column(name = "technic", columnDefinition = "TEXT")
   private String technic;
   @Column(name = "access", columnDefinition = "TEXT")
   private String access;
   @Column(name = "manager_id", columnDefinition = "INTEGER(10) NOT NULL")
   private Integer managerId;
   @Column(name = "climber_id", columnDefinition = "INTEGER(10)")
   private Integer climberId;
   @Enumerated(EnumType.STRING)
   @Column(name = "status", columnDefinition = "VARCHAR(15) DEFAULT 'UNVAILABLE' NOT NULL")
   private StatusType status;
   @Column(name = "status_auto", columnDefinition = "BOOLEAN DEFAULT FALSE")
   private Boolean statusAuto;

   public Topo() {
      super();
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

   public Integer getManagerId() {
      return managerId;
   }

   public void setManagerId(Integer managerId) {
      this.managerId = managerId;
   }

   public Integer getClimberId() {
      return climberId;
   }

   public void setClimberId(Integer climberId) {
      this.climberId = climberId;
   }

   public StatusType getStatus() {
      return status;
   }

   public void setStatus(StatusType status) {
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
      if (!(o instanceof Topo)) return false;
      if (!super.equals(o)) return false;
      Topo topo = (Topo) o;
      return getRegion().equals(topo.getRegion()) &&
            getAddressId().equals(topo.getAddressId()) &&
            getDate().equals(topo.getDate()) &&
            getDescription().equals(topo.getDescription()) &&
            Objects.equals(getTechnic(), topo.getTechnic()) &&
            Objects.equals(getAccess(), topo.getAccess()) &&
            getManagerId().equals(topo.getManagerId()) &&
            Objects.equals(getClimberId(), topo.getClimberId()) &&
            getStatus() == topo.getStatus() &&
            getStatusAuto().equals(topo.getStatusAuto());
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), getRegion(), getAddressId(), getDate(), getDescription(), getTechnic(), getAccess(), getManagerId(), getClimberId(), getStatus(), getStatusAuto());
   }

   @Override
   public String toString() {
      return "Topo{" +
            ", id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", region='" + region + '\'' +
            ", addressId=" + addressId +
            ", date=" + date +
            ", description='" + description + '\'' +
            ", technic='" + technic + '\'' +
            ", access='" + access + '\'' +
            ", managerId=" + managerId +
            ", climberId=" + climberId +
            ", status=" + status +
            ", statusAuto=" + statusAuto +
            ", hasComment=" + hasComment +
            ", photoLink='" + photoLink + '\'' +
            ", mapLink='" + mapLink + '\'' +
            '}';
   }
}
