package org.fesc.sicier.services.dtos.response;

import lombok.Data;
import org.fesc.sicier.persistence.entities.RequestStates;

@Data
public class RequestResponseDto {

    private RequestStates newState;
    private String response;

}
