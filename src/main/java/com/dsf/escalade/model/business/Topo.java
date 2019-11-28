package com.dsf.escalade.model.business;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "topo")
@PrimaryKeyJoinColumn(name = "site_id")
public class Topo extends Site{
   @Column(name = "region", columnDefinition = "VARCHAR(50)")
   private String region;
   @Column(name = "address_id", columnDefinition = "INTEGER(10)")
   private Integer address;
   @Column(name = "date", columnDefinition = "DATE DEFAULT NOW()")
   private Date date;
   @Column(name = "description", columnDefinition = "TEXT")
   private String description;
   @Column(name = "technic", columnDefinition = "TEXT")
   private String technic;
   @Column(name = "acces", columnDefinition = "TEXT")
   private String acces;
   @Column(name = "manager_id", columnDefinition = "INTEGER(10) NOT NULL")
   private Long manager;
   @Column(name = "climber_id", columnDefinition = "INTEGER(10)")
   private Long climber;
   @Enumerated(EnumType.STRING)
   @Column(name = "statut", columnDefinition = "VARCHAR(15) DEFAULT 'Indisponible' NOT NULL")
   private StatutType statut;
   @Column(name = "statut_auto", columnDefinition = "BOOLEAN DEFAULT FALSE")
   private Boolean statutAuto;

   public Topo() {
      super();
   }

   public Topo(String name, SiteType type, String region, Integer address, Date date, String description, Long manager) {
      super(name, type);
      this.region = region;
      this.address = address;
      this.date = date;
      this.description = description;
      this.manager = manager;
   }

   public Topo(int id, @NotBlank(message = "Spécifiez un nom !") String name, SiteType type, String photoLink, String mapLink, String region, Integer address, Date date, String description, Long manager) {
      super(id, name, type, photoLink, mapLink);
      this.region = region;
      this.address = address;
      this.date = date;
      this.description = description;
      this.manager = manager;
   }

   public String getRegion() {
      return region;
   }

   public void setRegion(String region) {
      this.region = region;
   }

   public Integer getAddress() {
      return address;
   }

   public void setAddress(Integer address) {
      this.address = address;
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

   public Long getManager() {
      return manager;
   }

   public void setManager(Long manager) {
      this.manager = manager;
   }

   public Long getClimber() {
      return climber;
   }

   public void setClimber(Long climber) {
      this.climber = climber;
   }

   public StatutType getStatut() {
      return statut;
   }

   public void setStatut(StatutType statut) {
      this.statut = statut;
   }

   public Boolean getStatutAuto() {
      return statutAuto;
   }

   public void setStatutAuto(Boolean statutAuto) {
      this.statutAuto = statutAuto;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Topo)) return false;
      if (!super.equals(o)) return false;
      Topo topo = (Topo) o;
      return getRegion().equals(topo.getRegion()) &&
            getAddress().equals(topo.getAddress()) &&
            getDate().equals(topo.getDate()) &&
            getManager().equals(topo.getManager());
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), getRegion(), getAddress(), getDate(), getManager());
   }

   @Override
   public String toString() {
      return "Topo{" +
            "region='" + region + '\'' +
            ", address=" + address +
            ", date=" + date +
            ", description='" + description + '\'' +
            ", technic='" + technic + '\'' +
            ", acces='" + acces + '\'' +
            ", manager=" + manager +
            ", climber=" + climber +
            ", statut=" + statut +
            ", statutAuto=" + statutAuto +
            ", id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", hasComment=" + hasComment +
            ", photoLink='" + photoLink + '\'' +
            ", mapLink='" + mapLink + '\'' +
            '}';
   }
}
