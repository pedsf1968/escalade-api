package com.dsf.escalade.web.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
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
    static final String PHONE_REGEXP = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2}|)$";

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

    public UserDto() {  super();  }
}
