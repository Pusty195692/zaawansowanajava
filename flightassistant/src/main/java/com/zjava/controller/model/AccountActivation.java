package com.zjava.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Piotr on 19.06.2017.
 */
@Data
@NoArgsConstructor
public class AccountActivation {

    @NotNull
    @NotEmpty
    private String token;

}
