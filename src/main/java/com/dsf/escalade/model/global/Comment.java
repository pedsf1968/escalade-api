package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "site_id")
   private Long site_id;
   @Column(name = "grimpeur_id", nullable = false)
   private Long grimpeur_id;
   @Column(name = "text", columnDefinition = "TEXT")
   private String text;

   public Comment() {
      super();
   }

   public Comment(Long site_id, Long grimpeur_id, String text) {
      this.site_id = site_id;
      this.grimpeur_id = grimpeur_id;
      this.text = text;
   }

   public Long getSite_id() {
      return site_id;
   }

   public void setSite_id(Long site_id) {
      this.site_id = site_id;
   }

   public Long getGrimpeur_id() {
      return grimpeur_id;
   }

   public void setGrimpeur_id(Long grimpeur_id) {
      this.grimpeur_id = grimpeur_id;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Comment)) return false;
      Comment comment = (Comment) o;
      return getSite_id().equals(comment.getSite_id()) &&
            getGrimpeur_id().equals(comment.getGrimpeur_id()) &&
            getText().equals(comment.getText());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getSite_id(), getGrimpeur_id(), getText());
   }

   @Override
   public String toString() {
      final StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Comment [")
            .append("id=").append(site_id)
            .append("grimpeur=").append(grimpeur_id)
            .append("text=").append(text)
            .append("]");

      return stringBuilder.toString();
   }
}
