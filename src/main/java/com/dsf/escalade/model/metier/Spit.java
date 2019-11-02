package com.dsf.escalade.model.metier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Spit implements Serializable {
   @EmbeddedId
   @AttributeOverrides({
         @AttributeOverride(name = "topo", column = @Column(name = "topo_id", nullable = false)),
         @AttributeOverride(name = "numero", column = @Column(name = "numero", nullable = false))
   })
   private SpitPK spitPK;

   @Column(name = "secteur_id")
   private Integer secteur;
   @Column(name = "longueur_id")
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Spit)) return false;
      Spit spit = (Spit) o;
      return getSpitPK().equals(spit.getSpitPK()) &&
            secteur.equals(spit.secteur) &&
            longueur.equals(spit.longueur) &&
            cotation.equals(spit.cotation) &&
            estRelai.equals(spit.estRelai) &&
            commentaire.equals(spit.commentaire);
   }

   @Override
   public int hashCode() {
      return Objects.hash(getSpitPK(), secteur, longueur, cotation, estRelai, commentaire);
   }
}
