package com.dsf.escalade.web.dto;


import com.dsf.escalade.model.global.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {
    static final int FIRSTNAME_MIN = 1;
    static final int LASTNAME_MIN = 1;
    static final int ALIAS_MIN = 1;
    static final int PASSWORD_MIN = 4;
    static final int EMAIL_MIN = 1;

    @NotNull
    @Size(min = FIRSTNAME_MIN)
    private String firstName;

    @NotNull
    @Size(min = LASTNAME_MIN)
    private String lastName;

    @NotNull
    @Size(min = ALIAS_MIN)
    private String alias;

    @NotNull
    @Size(min = PASSWORD_MIN)
    private String password;

    @NotNull
    @Size(min = PASSWORD_MIN)
    private String matchingPassword;

    @NotNull
    @Size(min = EMAIL_MIN)
    private String email;

    private String phone;
    private Role role;

    // Adress section
    private String street1;
    private String street2;
    private String zipCode;
    private String city;
    private String country;

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

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean hasAddress(){
        boolean response = true;
        if(street1==null) {
            response =false;
        } else if(zipCode==null) {
            response =false;
        } else if(city==null) {
            response =false;
        }

        return response;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UserDto [firstName=").append(firstName)
              .append(", lastName=").append(lastName)
              .append(", alias=").append(alias)
              .append(", password=").append(password)
              .append(", matchingPassword=").append(matchingPassword)
              .append(", phone=").append(phone)
              .append(", email=").append(email)
              .append(", role=").append(role);

        if(hasAddress()) {
            stringBuilder.append(", street=").append(street1).append(street2)
                  .append(", zipCode=").append(zipCode)
                  .append(", city=").append(city)
                  .append(", country=").append(country);
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

}
