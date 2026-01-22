package org.fesc.sicier.services.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAreaReqRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull(message = "Se nesecitan destinos") Long areaDestId
) {
}
