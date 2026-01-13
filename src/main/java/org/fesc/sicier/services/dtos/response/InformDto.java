package org.fesc.sicier.services.dtos.response;

import lombok.Data;
import org.fesc.sicier.persistence.entities.DestinationInformEntity;
import org.fesc.sicier.persistence.entities.security.AreaEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Data
public class InformDto {

    private String title;
    private String description;
    private String status;
    private String creationDate;
    private List<DestinationInformDto> destinations= new ArrayList<>();
    private UserEntity UserEmisor;
    private AreaEntity areasEmisor;

}
