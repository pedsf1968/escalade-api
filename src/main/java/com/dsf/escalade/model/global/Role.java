package com.dsf.escalade.model.global;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   private String name;

   @ManyToMany(mappedBy = "roles")
   private Set<User> users;
}