package org.fesc.sicier.services.dtos.response;

import lombok.Data;

@Data
public class DestinationInformDto {

    private String status;
    private String receptionDate;
    private String dateReading;
    private AreaDto areaDestination;

}
