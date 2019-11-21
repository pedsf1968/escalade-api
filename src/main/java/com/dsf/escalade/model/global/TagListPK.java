package com.dsf.escalade.model.global;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TagListPK implements Serializable {
   @Column(name = "topo_id")
   private Long topo;
   @Column(name = "tag_id")
   private Long tag;

   public TagListPK() {
      super();
   }

   public Long getTopo() {
      return topo;
   }

   public void setTopo(Long topo) {
      this.topo = topo;
   }

   public Long getTag() {
      return tag;
   }

   public void setTag(Long tag) {
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
