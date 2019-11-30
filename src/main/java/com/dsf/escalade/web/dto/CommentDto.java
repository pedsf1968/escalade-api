package com.dsf.escalade.web.dto;

import com.dsf.escalade.model.global.Comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CommentDto implements Serializable {

   private Integer site_id;
   @NotNull
   @NotBlank
   private String alias;
   private String text;

   public CommentDto() {
      super();
   }

   public CommentDto(Integer site_id, String alias, String text) {
      this.site_id = site_id;
      this.alias = alias;
      this.text = text;
   }

   public CommentDto(Comment comment){
      this.site_id = comment.getSite_id();
      this.text = comment.getText();
   }

   public Integer getSite_id() {
      return site_id;
   }

   public void setSite_id(Integer site_id) {
      this.site_id = site_id;
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

}
