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
         @AttributeOverride(name = "topo", column = @Column(name = "topo_id", nullable = false)),
         @AttributeOverride(name = "numero", column = @Column(name = "numero", nullable = false))
   })
   private SpitPK spitPK;

   @Column(name = "secteur_id", columnDefinition = "INTEGER(10)")
   private Integer secteur;
   @Column(name = "longueur_id", columnDefinition = "INTEGER(10)")
   private Integer longueur;
   @Column(name = "cotation_id", columnDefinition = "INTEGER(2)")
   private Integer cotation;
   @Column(name = "est_relai", columnDefinition = "BOOLEAN DEFAULT FALSE")
   private Boolean estRelai;
   @Column(name = "commentaire", columnDefinition = "TEXT")
   private String commentaire;

   protected Spit() {
      super();
   }
}
