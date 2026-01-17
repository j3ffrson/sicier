package org.fesc.sicier.services;

import org.fesc.sicier.services.dtos.request.CreateAreaRequest;
import org.fesc.sicier.services.dtos.response.AreaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AreaService {

    Page<AreaDto> getAllAreas(Pageable pageable);
    AreaDto getAreaById(Long id);
    AreaDto getAreaByName(String name);
    AreaDto createArea(CreateAreaRequest createAreaRequest);
    AreaDto updateArea(CreateAreaRequest createAreaRequest,Long id);


}
