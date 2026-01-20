package org.fesc.sicier.services.dtos.request;

public record CreateAreaReqRequest(
        String title,
        String description,
        Long areaDestId
) {
}
