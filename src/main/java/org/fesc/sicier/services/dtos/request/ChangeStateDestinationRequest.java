package org.fesc.sicier.services.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.fesc.sicier.services.StateDestination;

public record ChangeStateDestinationRequest(
        @NotNull(message = "Se solicita un cambio de estado") StateDestination newState,
        @NotBlank(message = "añadir descripcion") String observation
) {
}
