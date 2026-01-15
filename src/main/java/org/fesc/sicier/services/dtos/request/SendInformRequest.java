package org.fesc.sicier.services.dtos.request;

import java.util.List;

public record SendInformRequest(
        Long informId,
        List<Long> areasDestination
) {
}
