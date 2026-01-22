package org.fesc.sicier.services;

import org.fesc.sicier.persistence.entities.DestinationInformEntity;
import org.fesc.sicier.persistence.entities.InformStates;
import org.fesc.sicier.persistence.entities.RequestStates;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.services.dtos.request.CreateInformRequest;
import org.fesc.sicier.services.dtos.response.InformDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface InformServices {

    Page<InformDto> getAllInform(Pageable pageable);
    Page<InformDto> getAllInformByUser(Long id,Pageable pageable);
    InformDto getInformById(Long id);
    InformDto getInformByTitle(String title);
    InformDto createInformDraft();
    InformDto createInformComplete(CreateInformRequest informRequest,Long id);
    void deleteInform(Long id);
    List<InformDto> bandejaAreaPorEstado(UserEntity user, StateDestination state);
    List<InformDto> bandejaUserPorEstado(UserEntity user, StateDestination state);

}
