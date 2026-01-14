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

import java.util.List;

@Mapper(componentModel = "spring")
public interface InformMapper {

    InformDto toInformDto(InformEntity informEntity);
    List<InformDto> toInformDtoList(List<InformEntity> informEntities);
    InformEntity toInformEntity(InformDto informDto);
    AreaDto toAreaDto(AreaEntity areaEntity);
    DestinationInformDto toDestinationInformDto(DestinationInformEntity destinationInformEntity);
    UserDto toUserDto(UserEntity userEntity);
    @Mapping(target = "destinations",ignore = true)
    InformEntity requestToInformEntity(CreateInformRequest createInformRequest);

}
