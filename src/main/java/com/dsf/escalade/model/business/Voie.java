package com.dsf.escalade.model.business;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "voie")
@PrimaryKeyJoinColumn(name = "site_id")
public class Voie extends Site {
   @Column(name = "parent_id", columnDefinition = "INTEGER(10) NOT NULL")
   private Integer parentId;
   @Column(name = "is_equipped", columnDefinition = "BOOLEAN DEFAULT FALSE")
   private Boolean isEquipped;
   @Column(name = "heigth", columnDefinition = "INTEGER(3)")
   private Integer heigth;
   @Column(name = "cotation_id", columnDefinition = "VARCHAR(2)")
   private Integer cotationId;

   public Voie() {
      super();
   }
}
