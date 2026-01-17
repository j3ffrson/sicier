package org.fesc.sicier.services.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UsersAreaRequest(
        @NotEmpty @Size(max = 3,message = "Limite Alcanzado") List<Long> identifier
) {
}
