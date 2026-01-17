package org.fesc.sicier.services.dtos.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InformDto {

    private String title;
    private String description;
    private String status;
    private String creationDate;
    private List<DestinationInformDto> destinations= new ArrayList<>();
    private UserDto userEmisor;
    private AreaDto areaEmisor;

}
