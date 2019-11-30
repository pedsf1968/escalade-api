package com.dsf.escalade.model.business;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "site")
@Inheritance(strategy = InheritanceType.JOINED)
public class Site implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", columnDefinition = "INTEGER(10)")
   protected Integer id;
   @Column(name = "name", columnDefinition = "VARCHAR(50) NOT NULL")
   @NotBlank(message = "Spécifiez un nom !")
   protected String name;
   @Column(name = "type", columnDefinition = "VARCHAR(7)  NOT NULL DEFAULT 'TOPO'")
   @Enumerated(EnumType.STRING)
   protected SiteType type;
   @Column(name = "longitude", columnDefinition = "VARCHAR(10) DEFAULT NULL")
   private String longitude;
   @Column(name = "latitude", columnDefinition = "VARCHAR(10) DEFAULT NULL")
   private String latitude;
   @Column(name = "has_comment", columnDefinition = "BOOLEAN DEFAULT FALSE")
   protected Boolean hasComment;
   @Column(name = "photo_url", columnDefinition = "VARCHAR(255) DEFAULT NULL")
   protected String photoLink;
   @Column(name = "map_url", columnDefinition = "VARCHAR(255) DEFAULT NULL")
   protected String mapLink;

   public Site() {
      super();
   }

   public Site(String name, SiteType type) {
      super();
      this.name = name;
      this.type = type;
      this.hasComment = false;
      this.photoLink = null;
      this.mapLink = null;
   }


   public Site(Integer id, @NotBlank(message = "Spécifiez un nom !") String name, SiteType type, String photoLink, String mapLink) {
      super();
      this.id = id;
      this.name = name;
      this.type = type;
      this.hasComment = false;
      this.photoLink = photoLink;
      this.mapLink = mapLink;
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

   public SiteType getType() {
      return type;
   }

   public void setType(SiteType type) {
      this.type = type;
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Site)) return false;
      Site site = (Site) o;
      return getId() == site.getId() &&
            getName().equals(site.getName()) &&
            getType() == site.getType() &&
            Objects.equals(getLongitude(), site.getLongitude()) &&
            Objects.equals(getLatitude(), site.getLatitude()) &&
            getHasComment().equals(site.getHasComment()) &&
            Objects.equals(getPhotoLink(), site.getPhotoLink()) &&
            Objects.equals(getMapLink(), site.getMapLink());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getName(), getType(), getLongitude(), getLatitude(), getHasComment(), getPhotoLink(), getMapLink());
   }

   @Override
   public String toString() {
      return "Site{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", longitude='" + longitude + '\'' +
            ", latitude='" + latitude + '\'' +
            ", hasComment=" + hasComment +
            ", photoLink='" + photoLink + '\'' +
            ", mapLink='" + mapLink + '\'' +
            '}';
   }
}
