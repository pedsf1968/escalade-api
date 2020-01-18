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
@Table(name = "users")
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
   static final String PHONE_REGEXP = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2}|)$";


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

   @Column(name = "is_member", columnDefinition = "BOOLEAN DEFAULT FALSE")
   private Boolean isMember;
   @Column(name = "address_id")
   private Integer addressId;

   @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
   @JoinTable(name="users_roles",
         joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
         inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
   )
   private Set<Role> roles;

   @Column(name = "photo_url", columnDefinition = "VARCHAR(255) DEFAULT NULL")
   private String photoLink;

   public User() {
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

   public void setAddressId(Integer addressId) {
      this.addressId = addressId;
   }


   public String getPhotoLink() {
      return photoLink;
   }


   public void setPhotoLink(String photoLink) {
      this.photoLink = photoLink;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   public void addRole(Role role) {
      if(this.roles==null){
         this.roles = new HashSet<>();
      }
      this.roles.add(role);
      role.getUsers().add(this);
   }

   public void removeRole(Role role){
      if(this.roles!=null){
         this.roles.remove(role);
         role.getUsers().remove(this);
      }
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof User)) return false;
      User user = (User) o;
      return getId().equals(user.getId()) &&
            getFirstName().equals(user.getFirstName()) &&
            getLastName().equals(user.getLastName()) &&
            getPhone().equals(user.getPhone()) &&
            getEmail().equals(user.getEmail()) &&
            getAlias().equals(user.getAlias()) &&
            getPassword().equals(user.getPassword()) &&
            isMember.equals(user.isMember) &&
            getAddressId().equals(user.getAddressId()) &&
            getRoles().equals(user.getRoles()) &&
            getPhotoLink().equals(user.getPhotoLink());
   }



}
