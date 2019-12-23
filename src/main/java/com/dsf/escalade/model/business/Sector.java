package com.dsf.escalade.model.business;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@Table(name="sector")
@PrimaryKeyJoinColumn(name = "site_id")
public class Sector extends Site{
   @Column(name = "topoId", columnDefinition = "INTEGER(10) NOT NULL")
   private Integer topoId;
   @Column(name = "equipment", columnDefinition = "TEXT")
   private String equipment;

   public Sector() {
      super();
   }
}
