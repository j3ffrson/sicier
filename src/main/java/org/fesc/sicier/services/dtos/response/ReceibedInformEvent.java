package org.fesc.sicier.services.dtos.response;

import lombok.AllArgsConstructor;
import org.fesc.sicier.persistence.entities.security.AreaEntity;

import java.time.LocalDateTime;
@AllArgsConstructor
public class ReceibedInformEvent {

    private Long informId;
    private String title;
    private AreaEntity areaEmisor;
    private LocalDateTime date;

}
