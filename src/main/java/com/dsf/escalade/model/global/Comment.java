package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;
   @Column(name = "site_id", nullable = false)
   private Integer siteId;
   @Column(name = "user_id", nullable = false)
   private Integer userId;
   @Column(name = "text", nullable = false, columnDefinition = "TEXT")
   private String text;

   public Comment() {
      super();
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getSiteId() {
      return siteId;
   }

   public void setSiteId(Integer siteId) {
      this.siteId = siteId;
   }

   public Integer getUserId() {
      return userId;
   }

   public void setUserId(Integer userId) {
      this.userId = userId;
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
      return getId().equals(comment.getId()) &&
            getSiteId().equals(comment.getSiteId()) &&
            getUserId().equals(comment.getUserId()) &&
            getText().equals(comment.getText());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getSiteId(), getUserId(), getText());
   }

   @Override
   public String toString() {
      return "Comment{" +
            "id=" + id +
            ", siteId=" + siteId +
            ", userId=" + userId +
            ", text='" + text + '\'' +
            '}';
   }
}
