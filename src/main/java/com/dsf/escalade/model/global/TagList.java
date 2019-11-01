package com.dsf.escalade.model.global;

public class TagList {
   private Integer topo;
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

   @Override
   public String toString() {
      return "TagList{" +
            "topo=" + topo +
            ", tag=" + tag +
            '}';
   }
}
