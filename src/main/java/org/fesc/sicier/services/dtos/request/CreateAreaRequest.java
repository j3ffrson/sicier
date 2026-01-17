package org.fesc.sicier.services.dtos.request;


import jakarta.validation.Valid;

public record CreateAreaRequest(
        String name,
        String description,
        boolean active,
        @Valid UsersAreaRequest usersAreaRequest
) {
}
