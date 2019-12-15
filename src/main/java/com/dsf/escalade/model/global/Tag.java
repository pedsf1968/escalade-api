package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tag")
public class Tag implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;
   @Column(name = "name", columnDefinition = "VARCHAR(50) NOT NULL")
   private String name;

   public Tag() {
   }

   public Tag(Integer id, String name) {
      this.id = id;
      this.name = name;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return "Tag{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
   }
}
