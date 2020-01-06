package com.dsf.escalade.model.business;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Data
@Table(name = "site")
@Inheritance(strategy = InheritanceType.JOINED)
public class Site implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", columnDefinition = "INTEGER(10)")
   protected Integer id;
   @NotNull
   @NotBlank
   @Column(name = "name", columnDefinition = "VARCHAR(50) NOT NULL")
   protected String name;
   @Column(name = "type", columnDefinition = "VARCHAR(7)  NOT NULL DEFAULT 'TOPO'")
   @Enumerated(EnumType.STRING)
   protected SiteType type;
   @Column(name = "manager_id", columnDefinition = "INTEGER(10) NOT NULL")
   private Integer managerId;
   @Column(name = "longitude", columnDefinition = "VARCHAR(10) DEFAULT NULL")
   private String longitude;
   @Column(name = "latitude", columnDefinition = "VARCHAR(10) DEFAULT NULL")
   private String latitude;
   @Column(name = "nb_comment", columnDefinition = "INTEGER(3) DEFAULT 0")
   protected Integer nbComment;
   @Column(name = "photo_url", columnDefinition = "VARCHAR(255) DEFAULT NULL")
   protected String photoLink;
   @Column(name = "map_url", columnDefinition = "VARCHAR(255) DEFAULT NULL")
   protected String mapLink;
}
