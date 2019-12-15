package com.dsf.escalade.model.global;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tag_list")
@IdClass(TagListId.class)
public class TagList implements Serializable {
   @Id
   private Integer topo;
   @Id
   private Integer tag;

   public TagList() {
   }

   public TagList(Integer topo, Integer tag) {
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


}
