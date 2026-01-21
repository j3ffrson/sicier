package org.fesc.sicier.services.dtos.request;

public record CreateUserReqRequest(
        String title,
        String description,
        Long userDestId
) {
}
