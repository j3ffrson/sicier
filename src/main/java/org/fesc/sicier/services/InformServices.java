package org.fesc.sicier.services;

import org.fesc.sicier.services.dtos.request.CreateInformRequest;
import org.fesc.sicier.services.dtos.response.InformDto;

import java.util.List;

public interface InformServices {

    List<InformDto> getAllInform();
    List<InformDto> getAllInformByUsers(String user);
    List<InformDto> getAllInformByAreas(String area);
    InformDto getInformById(Long id);
    InformDto getInformByTitle(String title);
    InformDto createInformDraft();
    InformDto CreateInformComplete(CreateInformRequest informRequest,Long id);
    void deleteInform(Long id);


}
