package com.dsf.escalade.model.global;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity(name = "Role")
@Table(name = "role")
public class Role implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @NotNull
   @NotEmpty
   private String name;

   @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
   private Set<User> users;

}