package com.dsf.escalade.model.global;

public class Tag {
   private Integer id;
   private String nom;

   public Tag() {
   }

   public Tag(Integer id, String name) {
      this.id = id;
      this.nom = name;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getNom() {
      return nom;
   }

   public void setNom(String name) {
      this.nom = name;
   }

   @Override
   public String toString() {
      return "Tag{" +
            "id=" + id +
            ", name='" + nom + '\'' +
            '}';
   }
}
