package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   private String name;

   @ManyToMany(mappedBy = "roles")
   private Set<User> users;

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

   public Set<User> getUsers() {
      return users;
   }

   public void setUsers(Set<User> users) {
      this.users = users;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Role)) return false;
      Role role = (Role) o;
      return getId().equals(role.getId()) &&
            getName().equals(role.getName()) &&
            getUsers().equals(role.getUsers());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getName(), getUsers());
   }

   @Override
   public String toString() {
      final StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Role [")
            .append("id=").append(id)
            .append("name=").append(name)
            .append("]");

      return stringBuilder.toString();
   }
}