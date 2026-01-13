package org.fesc.sicier.services.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AuthCreateUserRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String institutionalEmail,
        @Positive Long identifier,
        @Positive Long phone,
        @NotBlank String username,
        @NotBlank String password,
        @Valid AuthCreateRoleRequest roleRequest
){}
