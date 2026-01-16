package org.fesc.sicier.services;

import org.fesc.sicier.services.dtos.request.CreateInformRequest;
import org.fesc.sicier.services.dtos.response.InformDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface InformServices {

    Page<InformDto> getAllInform(Pageable pageable);
    Page<InformDto> getAllInformByUser(Long id,Pageable pageable);
    InformDto getInformById(Long id);
    InformDto getInformByTitle(String title);
    InformDto createInformDraft();
    InformDto createInformComplete(CreateInformRequest informRequest,Long id);
    InformDto changeGlobalState(Long id,String state);
    void deleteInform(Long id);


}
