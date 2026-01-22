package org.fesc.sicier.services.dtos.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record SendInformRequest(
        @NotEmpty(message = "Los destinos son obligatorios") List<Long> destinations
) {
}
