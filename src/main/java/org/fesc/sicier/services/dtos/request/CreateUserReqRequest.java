package org.fesc.sicier.services.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserReqRequest(
        @NotBlank String title,
        String description,
        @NotNull Long userDestId
) {
}
