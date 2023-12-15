package com.platform.integration.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestBody {

    @NotNull(message = "email is required")
    @NotEmpty(message = "email can't be empty")
    @Email(message = "email format is invalid")
    private String email;

    @NotNull(message = "password is required")
    @NotEmpty(message = "password can't be required")
    private String password;
}