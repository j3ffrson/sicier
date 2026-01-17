package org.fesc.sicier.services.implementations;

import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.security.AreaEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.repositories.AreaRepository;
import org.fesc.sicier.persistence.repositories.UserRepository;
import org.fesc.sicier.services.AreaService;
import org.fesc.sicier.services.dtos.request.CreateAreaRequest;
import org.fesc.sicier.services.dtos.request.UsersAreaRequest;
import org.fesc.sicier.services.dtos.response.AreaDto;
import org.fesc.sicier.utils.InformMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;
    private final UserRepository userRepository;
    private final InformMapper informMapper;

    @Override
    public Page<AreaDto> getAllAreas(Pageable pageable) {
        return areaRepository.findAll(pageable).map(informMapper::toAreaDto);
    }

    @Override
    public AreaDto getAreaById(Long id) {
        return informMapper.toAreaDto(areaRepository.findById(id).orElseThrow());
    }

    @Override
    public AreaDto getAreaByName(String name) {
        return informMapper.toAreaDto(areaRepository.findByName(name).orElseThrow());
    }

    @Override
    public AreaDto createArea(CreateAreaRequest createAreaRequest) {

        AreaEntity area= AreaEntity.builder()
                .name(createAreaRequest.name())
                .description(createAreaRequest.description())
                .active(createAreaRequest.active())
                .users(Collections.emptyList())
                .build();

        areaRepository.save(area);
        return informMapper.toAreaDto(area);
    }

    @Override
    public AreaDto updateArea(CreateAreaRequest createAreaRequest, Long id) {
        AreaEntity area= areaRepository.findById(id).orElseThrow();

        area.setId(id);
        area.setName(createAreaRequest.name());
        area.setDescription(createAreaRequest.description());
        area.setActive(createAreaRequest.active());
        areaRepository.save(area);

        return null;
    }
}
