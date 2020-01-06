package com.dsf.escalade.model.business;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
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
   @NotNull
   @NotBlank
   @Column(name = "description", columnDefinition = "TEXT")
   private String description;
   @Column(name = "technic", columnDefinition = "TEXT")
   private String technic;
   @Column(name = "access", columnDefinition = "TEXT")
   private String access;
   @Column(name = "climber_id", columnDefinition = "INTEGER(10)")
   private Integer climberId;
   @Enumerated(EnumType.STRING)
   @Column(name = "status", columnDefinition = "VARCHAR(15) DEFAULT 'UNVAILABLE' NOT NULL")
   private StatusType status;
   @Column(name = "status_auto", columnDefinition = "BOOLEAN DEFAULT FALSE")
   private Boolean statusAuto;
   @Column(name = "cotation_min", columnDefinition = "VARCHAR(2)")
   private Integer cotationMin;
   @Column(name = "cotation_max", columnDefinition = "VARCHAR(2)")
   private Integer cotationMax;
   @Column(name = "nb_lane", columnDefinition = "INTEGER(3) DEFAULT 0")
   protected Integer nbLane;

}
