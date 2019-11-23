package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   private String name;

   @ManyToMany(mappedBy = "roles")
   private Set<Utilisateur> utilisateurs;

   public Role() {
      super();
   }

   public Role(final String name) {
      super();
      this.name = name;
   }

     public Long getId() {
      return id;
   }

   public void setId(final Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(final String name) {
      this.name = name;
   }

   public Set<Utilisateur> getUtilisateurs() {
      return utilisateurs;
   }

   public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
      this.utilisateurs = utilisateurs;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Role)) return false;
      Role role = (Role) o;
      return getId().equals(role.getId()) &&
            getName().equals(role.getName()) &&
            getUtilisateurs().equals(role.getUtilisateurs());
   }

   @Override
   public String toString() {
      final StringBuilder builder = new StringBuilder();
      builder.append("Role [name=").append(name).append("]").append("[id=").append(id).append("]");
      return builder.toString();
   }
}