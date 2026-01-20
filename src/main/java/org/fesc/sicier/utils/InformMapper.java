package org.fesc.sicier.utils;

import org.fesc.sicier.persistence.entities.DestinationInformEntity;
import org.fesc.sicier.persistence.entities.InformEntity;
import org.fesc.sicier.persistence.entities.security.AreaEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.services.dtos.request.CreateInformRequest;
import org.fesc.sicier.services.dtos.response.AreaDto;
import org.fesc.sicier.services.dtos.response.DestinationInformDto;
import org.fesc.sicier.services.dtos.response.InformDto;
import org.fesc.sicier.services.dtos.response.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface InformMapper {

    InformDto toInformDto(InformEntity informEntity);
    InformEntity toInformEntity(InformDto informDto);
    AreaDto toAreaDto(AreaEntity areaEntity);
    DestinationInformDto toDestinationInformDto(DestinationInformEntity destinationInformEntity);
    UserDto toUserDto(UserEntity userEntity);
    InformEntity requestToInformEntity(CreateInformRequest createInformRequest);

    default String mapCreationDate(LocalDateTime creationDate){
        return creationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"));
    }

}
