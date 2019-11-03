package com.dsf.escalade.model.global;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TagListPK implements Serializable {
   @Column(name = "topo_id", columnDefinition = "INTEGER(10)")
   private Integer topo;
   @Column(name = "tag_id", columnDefinition = "INTEGER(2)")
   private Integer tag;

   public TagListPK() {
   }

   public TagListPK(Integer topo, Integer tag) {
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
      if (!(o instanceof TagListPK)) return false;
      TagListPK tagListPK = (TagListPK) o;
      return getTopo().equals(tagListPK.getTopo()) &&
            getTag().equals(tagListPK.getTag());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getTopo(), getTag());
   }
}
