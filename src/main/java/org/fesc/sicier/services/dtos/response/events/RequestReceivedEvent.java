package org.fesc.sicier.services.dtos.response.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RequestReceivedEvent {

    Long idRequest;
    String title;
    String description;
    String requested;
    LocalDateTime date;

}
