package com.dsf.escalade.web.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDto {
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

    private Integer id;

    @NotNull
    @Size(min = FIRSTNAME_MIN, max = FIRSTNAME_MAX)
    private String firstName;

    @NotNull
    @Size(min = LASTNAME_MIN, max = LASTNAME_MAX)
    private String lastName;

    @NotNull
    @Size(min = ALIAS_MIN, max = ALIAS_MAX)
    private String alias;

    @NotNull
    @Size(min = PASSWORD_MIN, max = PASSWORD_MAX)
    private String password;

    @NotNull
    @Size(min = PASSWORD_MIN, max = PASSWORD_MAX)
    private String matchingPassword;

    @NotNull
    @Size(min = EMAIL_MIN, max = EMAIL_MAX)
    @Pattern(regexp = EMAIL_REGEXP, message = "Not a valid email address !")
    private String email;

    @Size(max = PHONE_MAX)
    @Pattern(regexp = PHONE_REGEXP, message = "Not a valid phone number !")
    private String phone;
    private Integer addressId;
    private List<String> roles;

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void addRole(String role) {
        if(this.roles == null)
            this.roles = new ArrayList<String>();

        this.roles.add(role);
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        UserDto userDto = (UserDto) o;
        return getFirstName().equals(userDto.getFirstName()) &&
              getLastName().equals(userDto.getLastName()) &&
              getAlias().equals(userDto.getAlias()) &&
              getPassword().equals(userDto.getPassword()) &&
              Objects.equals(getMatchingPassword(), userDto.getMatchingPassword()) &&
              getEmail().equals(userDto.getEmail()) &&
              Objects.equals(getPhone(), userDto.getPhone()) &&
              Objects.equals(getRoles(), userDto.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getAlias(), getPassword(), getMatchingPassword(), getEmail(), getPhone(), getRoles());
    }

    @Override
    public String toString() {
        return "UserDto{" +
              "id='" + id + '\'' +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", alias='" + alias + '\'' +
              ", password='" + password + '\'' +
              ", matchingPassword='" + matchingPassword + '\'' +
              ", email='" + email + '\'' +
              ", phone='" + phone + '\'' +
              ", roles=" + roles +
              '}';
    }
}
