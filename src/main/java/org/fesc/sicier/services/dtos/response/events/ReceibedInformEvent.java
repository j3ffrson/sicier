package org.fesc.sicier.services.dtos.response.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceibedInformEvent {

    Long informId;
    String title;
    String issuer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime date;

}
