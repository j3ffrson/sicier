package org.fesc.sicier.services.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateInformRequest(

        @NotBlank(message = "Se nesecita de un titulo") String title,
        @NotBlank(message = "Describe el area en especifico") String description,
        @NotBlank String status
) {}
