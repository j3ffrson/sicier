package org.fesc.sicier.utils;

import org.fesc.sicier.persistence.entities.DestinationInformEntity;
import org.fesc.sicier.persistence.entities.InformEntity;
import org.fesc.sicier.persistence.entities.AreaEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.services.dtos.request.CreateInformRequest;
import org.fesc.sicier.services.dtos.response.AreaDto;
import org.fesc.sicier.services.dtos.response.DestinationInformDto;
import org.fesc.sicier.services.dtos.response.InformDto;
import org.fesc.sicier.services.dtos.response.UserDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface InformMapper {

    InformDto toInformDto(InformEntity informEntity);
    DestinationInformDto toDestinationInformDto(DestinationInformEntity destinationInformEntity);
    UserDto toUserDto(UserEntity userEntity);
    InformEntity requestToInformEntity(CreateInformRequest createInformRequest);
    AreaDto toAreaDto(AreaEntity areaEntity);

    default String mapCreationDate(LocalDateTime creationDate){
        return creationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"));
    }

}
