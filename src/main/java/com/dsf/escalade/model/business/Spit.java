package com.dsf.escalade.model.business;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "spit")
public class Spit implements Serializable {
   @EmbeddedId
   @AttributeOverrides({
         @AttributeOverride(name = "topoId", column = @Column(name = "topo_id", nullable = false)),
         @AttributeOverride(name = "number", column = @Column(name = "number", nullable = false))
   })
   private SpitPK spitPK;

   @Column(name = "voie_id", columnDefinition = "INTEGER(10)")
   private Integer voieId;
   @Column(name = "longueur_id", columnDefinition = "INTEGER(10)")
   private Integer longueurId;
   @Column(name = "cotation_id", columnDefinition = "INTEGER(2)")
   private Integer cotationId;
   @Column(name = "is_relay", columnDefinition = "BOOLEAN DEFAULT FALSE")
   private Boolean isRelay;
   @Column(name = "comment", columnDefinition = "TEXT")
   private String comment;

   public Spit() {
      super();
   }
}
