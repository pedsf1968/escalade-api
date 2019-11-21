package com.dsf.escalade.model.metier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
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

   public SpitPK getSpitPK(){
      return spitPK;
   }

   protected Spit() {
      super();
   }

   public Spit(SpitPK spitPK, Integer secteur, Integer longueur, Integer cotation, Boolean estRelai, String commentaire) {
      this.spitPK = spitPK;
      this.secteur = secteur;
      this.longueur = longueur;
      this.cotation = cotation;
      this.estRelai = estRelai;
      this.commentaire = commentaire;
   }

   public void setSpitPK(SpitPK spitPK) {
      this.spitPK = spitPK;
   }

   public Integer getSecteur() {
      return secteur;
   }

   public void setSecteur(Integer secteur) {
      this.secteur = secteur;
   }

   public Integer getLongueur() {
      return longueur;
   }

   public void setLongueur(Integer longueur) {
      this.longueur = longueur;
   }

   public Integer getCotation() {
      return cotation;
   }

   public void setCotation(Integer cotation) {
      this.cotation = cotation;
   }

   public Boolean getEstRelai() {
      return estRelai;
   }

   public void setEstRelai(Boolean estRelai) {
      this.estRelai = estRelai;
   }

   public String getCommentaire() {
      return commentaire;
   }

   public void setCommentaire(String commentaire) {
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
