package com.freedom.music.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.freedom.music.common.constants.StringConstants.*;

@Data
public class UserDTO {

    @NotBlank(message = INVALID_NAME)
    @Pattern(regexp = "^[a-zA-Z ]*$", message = INVALID_NAME)
    @Length(min = 3, max = 100, message = INVALID_NAME)
    private String name;

    @NotBlank(message = INVALID_CITY)
    @Pattern(regexp = "^[a-zA-Z]*$", message = INVALID_CITY)
    @Length(min = 3, max = 100, message = INVALID_CITY)
    private String city;

    @NotBlank(message = INVALID_USER_TYPE)
    @Pattern(regexp = "MUSICIAN|NORMAL|ADMIN|musician|normal|admin", message = INVALID_USER_TYPE)
    private String type;

    @NotBlank(message = INVALID_STATE)
    @Pattern(regexp = "^[a-zA-Z]*$", message = INVALID_STATE)
    @Length(min = 3, max = 100, message = INVALID_STATE)
    private String state;

    @NotBlank(message = INVALID_EMAIL)
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = INVALID_EMAIL)
    @Length(min = 3, max = 100, message = INVALID_EMAIL)
    private String email;

    @NotBlank(message = INVALID_COUNTRY)
    @Pattern(regexp = "^[a-zA-Z]*$", message = INVALID_COUNTRY)
    @Length(min = 3, max = 100, message = INVALID_COUNTRY)
    private String country;

    @NotBlank(message = INVALID_CONTACT_NO)
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$", message = INVALID_CONTACT_NO)
    @Length(min = 10, max = 10, message = INVALID_CONTACT_NO)
    private String contactNo;

    @NotBlank(message = INVALID_DESCRIPTION)
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = INVALID_DESCRIPTION)
    @Length(min = 3, max = 100, message = INVALID_DESCRIPTION)
    private String description;

    private String status;
}