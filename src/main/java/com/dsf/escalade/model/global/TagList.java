package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class TagList implements Serializable {
   @EmbeddedId
   @AttributeOverrides({
         @AttributeOverride(name = "topo", column = @Column(name = "topo_id", nullable = false)),
         @AttributeOverride(name = "tag", column = @Column(name = "tag_id", nullable = false))
         })
   private TagListPK tagListPK;

   public TagList() {
      super();
   }

   public TagList(TagListPK tagListPK) {
      this.tagListPK = tagListPK;
   }

   public TagListPK getTagListPK() {
      return tagListPK;
   }

   public void setTagListPK(TagListPK tagListPK) {
      this.tagListPK = tagListPK;
   }

   @Override
   public String toString() {
      return "TagList{" +
            "tagListPK=" + tagListPK +
            '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof TagList)) return false;
      TagList tagList = (TagList) o;
      return getTagListPK().equals(tagList.getTagListPK());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getTagListPK());
   }
}
