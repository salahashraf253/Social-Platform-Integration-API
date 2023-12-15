package com.platform.integration.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestBody {

    @NotNull(message = "firstName is required")
    @NotEmpty(message = "firstName can't be empty")
    private String firstName;

    @NotNull(message = "lastName is required")
    @NotEmpty(message = "lastName can't be empty")
    private String lastName;

    @NotNull(message = "email cis required")
    @NotEmpty(message = "email can't be empty")
    @Email(message = "email format is invalid")
    private String email;

    @NotNull(message = "password is required")
    @NotEmpty(message = "password can't be empty")
    @Size(min = 8, message = "minimum length of password is 8")
    private String password;

}