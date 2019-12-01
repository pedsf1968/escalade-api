package com.dsf.escalade.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class CommentDto implements Serializable {

   private Integer id;
   private Integer siteId;
   @NotNull
   @NotBlank
   private String alias;
   private String text;

   public CommentDto() {
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

   public String getAlias() {
      return alias;
   }

   public void setAlias(String alias) {
      this.alias = alias;
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
      if (!(o instanceof CommentDto)) return false;
      CommentDto that = (CommentDto) o;
      return getId().equals(that.getId()) &&
            getSiteId().equals(that.getSiteId()) &&
            getAlias().equals(that.getAlias()) &&
            getText().equals(that.getText());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getSiteId(), getAlias(), getText());
   }

   @Override
   public String toString() {
      return "CommentDto{" +
            "id=" + id +
            ", siteId=" + siteId +
            ", alias='" + alias + '\'' +
            ", text='" + text + '\'' +
            '}';
   }
}
