package org.fesc.sicier.services.dtos.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CreateAreaRequest(
        @NotBlank String name,
        @NotBlank String description,
        boolean active,
        @Valid UsersAreaRequest usersAreaRequest
) {
}
