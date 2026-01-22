package org.fesc.sicier.services.dtos.response.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RequestResponseEvent {

    Long idRequest;
    String newState;
    String response;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime date;

}
