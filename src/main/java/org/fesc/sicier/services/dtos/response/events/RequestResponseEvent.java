package org.fesc.sicier.services.dtos.response.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RequestResponseEvent {

    Long idRequest;
    String newState;
    String response;
    LocalDateTime date;

}
