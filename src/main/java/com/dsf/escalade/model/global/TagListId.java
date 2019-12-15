package com.dsf.escalade.model.global;

import java.io.Serializable;
import java.util.Objects;

public class TagListId implements Serializable {
   private Integer topo;
   private Integer tag;

   public TagListId() {
   }

   public TagListId(Integer topo, Integer tag) {
      this.topo = topo;
      this.tag = tag;
   }

   public Integer getTopo() {
      return topo;
   }

   public void setTopo(Integer topo) {
      this.topo = topo;
   }

   public Integer getTag() {
      return tag;
   }

   public void setTag(Integer tag) {
      this.tag = tag;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof TagListId)) return false;
      TagListId tagListPK = (TagListId) o;
      return getTopo().equals(tagListPK.getTopo()) &&
            getTag().equals(tagListPK.getTag());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getTopo(), getTag());
   }
}
