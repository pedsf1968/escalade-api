package com.dsf.escalade.model.global;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * class for user informations
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
   static final int FIRSTNAME_MIN = 1;
   static final int FIRSTNAME_MAX = 20;
   static final int LASTNAME_MIN = 1;
   static final int LASTNAME_MAX = 20;
   static final int ALIAS_MIN = 1;
   static final int ALIAS_MAX = 20;
   static final int PASSWORD_MIN = 4;
   static final int PASSWORD_MAX = 255;
   static final int EMAIL_MIN = 4;
   static final int EMAIL_MAX = 255;
   static final String EMAIL_REGEXP = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
   static final int PHONE_MAX = 14;
   static final String PHONE_REGEXP = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$";


   @Id
   @Column(name = "id")
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private Integer id;

   @NotNull
   @Size(min = FIRSTNAME_MIN, max = FIRSTNAME_MAX)
   @Column(name = "firstname")
   private String firstName;

   @NotNull
   @Size(min = LASTNAME_MIN, max = LASTNAME_MAX)
   @Column(name = "lastname")
   private String lastName;


   @Size(max = PHONE_MAX)
   @Pattern(regexp = PHONE_REGEXP, message = "Not a valid phone number !")
   @Column(name = "phone")
   private String phone;

   @NotNull
   @Size(min = EMAIL_MIN, max = EMAIL_MAX)
   @Pattern(regexp = EMAIL_REGEXP, message = "Not a valid email address !")
   @Column(name = "email", unique = true)
   private String email;

   @NotNull
   @Size(min = ALIAS_MIN, max = ALIAS_MAX)
   @Column(name = "alias", unique = true)
   private String alias;

   @NotNull
   @Size(min = PASSWORD_MIN, max = PASSWORD_MAX)
   @Column(name = "password")
   private String password;

   @Column(name = "is_member")
   private Boolean isMember;
   @Column(name = "address_id")
   private Integer addressId;

   @ManyToMany
   private Set<Role> roles;

   public User() {
      super();
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
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

   public Boolean getMember() {
      return isMember;
   }

   public void setMember(Boolean member) {
      isMember = member;
   }

   public Integer getAddressId() {
      return addressId;
   }

   public void setAdressId(Integer adressId) {
      this.addressId = adressId;
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
