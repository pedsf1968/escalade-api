package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private Integer id;
   @Column(name = "site_id", nullable = false)
   private Integer site_id;
   @Column(name = "user_id", nullable = false)
   private Integer user_id;
   @Column(name = "text", nullable = false, columnDefinition = "TEXT")
   private String text;

   public Comment() {
      super();
   }

   public Comment(Integer site_id, Integer user_id, String text) {
      this.site_id = site_id;
      this.user_id = user_id;
      this.text = text;
   }

   public Integer getSite_id() {
      return site_id;
   }

   public void setSite_id(Integer site_id) {
      this.site_id = site_id;
   }

   public Integer getUser_id() {
      return user_id;
   }

   public void setUser_id(Integer user_id) {
      this.user_id = user_id;
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
            getUser_id().equals(comment.getUser_id()) &&
            getText().equals(comment.getText());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getSite_id(), getUser_id(), getText());
   }

   @Override
   public String toString() {
      final StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Comment [")
            .append("id=").append(site_id)
            .append("grimpeur=").append(user_id)
            .append("text=").append(text)
            .append("]");

      return stringBuilder.toString();
   }
}
