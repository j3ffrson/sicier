package org.fesc.sicier.services;

import org.fesc.sicier.services.dtos.request.CreateInformRequest;
import org.fesc.sicier.services.dtos.response.InformDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InformServices {

    Page<InformDto> getAllInform(Pageable pageable);
    List<InformDto> getAllInformByUser(String user);
    List<InformDto> getAllInformByArea(String area);
    InformDto getInformById(Long id);
    InformDto getInformByTitle(String title);
    InformDto createInformDraft();
    InformDto createInformComplete(CreateInformRequest informRequest,Long id);
    InformDto changeGlobalState(Long id,String state);
    void deleteInform(Long id);


}
