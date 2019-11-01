package com.dsf.escalade.model.metier;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="spit", schema="public")
public class Spit implements Serializable {
   @EmbeddedId
   @AttributeOverrides({
         @AttributeOverride(name = "topo", column = @Column(name = "topo_id", nullable = false)),
         @AttributeOverride(name = "numero", column = @Column(name = "numero", nullable = false))
   })
   private SpitPK spitPK;

   private Integer secteur;
   private Integer longueur;
   private String cotation;
   private Boolean estRelai;
   private String commentaire;

   public SpitPK getSpitPK(){
      return spitPK;
   }

   protected Spit() {
      super();
   }

   public Spit(SpitPK spitPK, Integer secteur, Integer longueur, String cotation, Boolean estRelai, String commentaire) {
      this.spitPK = spitPK;
      this.secteur = secteur;
      this.longueur = longueur;
      this.cotation = cotation;
      this.estRelai = estRelai;
      this.commentaire = commentaire;
   }

   @Override
   public String toString() {
      return String.format(
            "Spit {topo=%s, " +
                  "numero='%d', " +
                  "secteur='%s'," +
                  "longueur='%s'," +
                  "cotation='%s', " +
                  "est relai='%b', " +
                  "commentaire='%s'}",
            spitPK.getTopo(), spitPK.getNumero(), secteur, longueur, cotation, estRelai, commentaire);
   }

}
