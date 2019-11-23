package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private Long id;

   @Column(name = "firstname", columnDefinition = "VARCHAR(50) NOT NULL")
   private String firstName;

   @Column(name = "lastname", columnDefinition = "VARCHAR(50) NOT NULL")
   private String lastName;

   @Column(name = "phone", columnDefinition = "VARCHAR(10)")
   private String phone;

   @Column(name = "email", columnDefinition = "VARCHAR(255) NOT NULL")
   private String email;

   @Column(name = "alias", columnDefinition = "VARCHAR(20) NOT NULL")
   private String alias;

   @Column(name = "password", columnDefinition = "VARCHAR(255) NOT NULL")
   private String password;
   @Column(name = "enabled")
   private boolean enabled;
   @Column(name = "token_expired")
   private boolean tokenExpired;

   @Column(name = "is_member")
   private Boolean isMember;
   @Column(name = "address_id")
   private Long adressId;

   @ManyToMany
   private Set<Role> roles;

   public User() {
      super();
   }

   public User(String firstName, String lastName, String phone, String email, String alias, String password) {
      super();
      this.firstName = firstName;
      this.lastName = lastName;
      this.phone = phone;
      this.email = email;
      this.alias = alias;
      this.password = password;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getAlias() {
      return alias;
   }

   public void setAlias(String alias) {
      this.alias = alias;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public boolean isEnabled() {
      return enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public boolean isTokenExpired() {
      return tokenExpired;
   }

   public void setTokenExpired(boolean tokenExpired) {
      this.tokenExpired = tokenExpired;
   }

   public Boolean getMember() {
      return isMember;
   }

   public void setMember(Boolean member) {
      isMember = member;
   }

   public Long getAdressId() {
      return adressId;
   }

   public void setAdressId(Long adressId) {
      this.adressId = adressId;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   public  void addRole(Role role){
      if(roles == null){
         roles = new HashSet<>();
      }
      roles.add(role);
   }

   @Override
   public String toString() {
      final StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("User [")
            .append("id=").append(id)
            .append(", first name=").append(firstName)
            .append(", last name=").append(lastName)
            .append(" alias=").append(alias)
            .append(", phone=").append(phone)
            .append(", email=").append(email)
            .append(", password=").append(password)
            .append(", roles=").append(roles)
            .append("]");

      return stringBuilder.toString();
   }
}
