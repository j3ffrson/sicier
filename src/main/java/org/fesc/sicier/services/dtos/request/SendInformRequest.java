package org.fesc.sicier.services.dtos.request;

import java.util.List;

public record SendInformRequest(
        List<Long> areasDestination
) {
}
