package org.fesc.sicier.services;

import org.fesc.sicier.services.dtos.request.CreateInformRequest;
import org.fesc.sicier.services.dtos.response.InformDto;

import java.util.List;

public interface InformServices {

    List<InformDto> getAllInform();
    InformDto getInform(Long id);
    InformDto createInform(CreateInformRequest informRequest);
    InformDto updateInformComplete(CreateInformRequest informRequest);
    InformDto changeGlobalState(CreateInformRequest informRequest);
    void deleteInform(Long id);


}
