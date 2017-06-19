package com.zjava.controller.model;

import com.zjava.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Piotr on 18.06.2017.
 */
@Data
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    @Size(min = 2, max = 40, message = "{validation.error.firstname.size}")
    @Pattern(regexp = "^\\p{L}{2,40}$", message = "{validation.error.firstname.format}")
    private String firstName;

    @Size(min = 2, max = 40, message = "{validation.error.lastname.size}")
    @Pattern(regexp = "^\\p{L}{2,40}$", message = "{validation.error.lastname.format}")
    private String lastName;

    @NonNull
    @Size(min = 6, max = 40, message = "{validation.error.email.size}")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "{validation.error.email.format}")
    private String email;

    @Size(min = 6, max = 64, message = "{validation.error.password.size}")
    private String password;

    @Pattern(regexp = "^(\\+48)[5-9][0-9]{8}$", message = "{validation.error.phonenumber.format}")
    private String phoneNumber;

    private Boolean isActive = false;
}
