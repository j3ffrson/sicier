package org.fesc.sicier.services.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateInformRequest(

        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String status,
        String userName,
        String areaName
) {}
