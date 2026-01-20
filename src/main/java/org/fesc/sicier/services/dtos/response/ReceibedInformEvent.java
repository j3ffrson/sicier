package org.fesc.sicier.services.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fesc.sicier.persistence.entities.security.AreaEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceibedInformEvent {

    Long informId;
    String title;
    String issuer;
    LocalDateTime date;

}
