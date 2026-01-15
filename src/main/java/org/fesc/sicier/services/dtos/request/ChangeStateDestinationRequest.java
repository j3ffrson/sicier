package org.fesc.sicier.services.dtos.request;

import org.fesc.sicier.services.StateDestination;

public record ChangeStateDestinationRequest(
        StateDestination newState,
        String observation
) {
}
