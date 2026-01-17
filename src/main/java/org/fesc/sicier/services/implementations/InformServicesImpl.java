package org.fesc.sicier.services.implementations;

import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.InformEntity;
import org.fesc.sicier.persistence.entities.InformStates;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.repositories.InformRepository;
import org.fesc.sicier.persistence.repositories.UserRepository;
import org.fesc.sicier.services.InformServices;
import org.fesc.sicier.services.dtos.request.CreateInformRequest;
import org.fesc.sicier.services.dtos.response.InformDto;
import org.fesc.sicier.utils.InformMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InformServicesImpl implements InformServices {

    private final InformRepository informRepository;
    private final UserRepository userRepository;
    private final InformMapper informMapper;

    @Override
    public Page<InformDto> getAllInform(Pageable pageable) {
        return informRepository.findAll(pageable).map(informMapper::toInformDto);
    }

    @Override
    public Page<InformDto> getAllInformByUser(Long id,Pageable pageable) {
        return informRepository.findInformEntitiesByUserEmisor_Id(id,pageable).map(informMapper::toInformDto);
    }


    @Override
    public InformDto getInformById(Long id) {
        return informMapper.toInformDto(informRepository.findById(id).orElseThrow());
    }

    @Override
    public InformDto getInformByTitle(String title) {
        return informMapper.toInformDto(informRepository.findByTitle(title).orElseThrow());
    }

    @Override
    public InformDto createInformDraft() {
        InformEntity informEntity= new InformEntity();
        informEntity.setCreationDate(LocalDateTime.now());
        informEntity.setStatus(InformStates.BORRADOR.name());
        informRepository.save(informEntity);
        return informMapper.toInformDto(informEntity);
    }

    @Override
    public InformDto createInformComplete(CreateInformRequest informRequest,Long id) {

        InformEntity informEntity= informMapper.requestToInformEntity(informRequest);
        informEntity.setId(id);
        informEntity.setCreationDate(LocalDateTime.now());

        UserEntity userEntity= userRepository.findByFirstName(informRequest.userName()).orElseThrow();
        informEntity.setUserEmisor(userEntity);
        informEntity.setAreaEmisor(userEntity.getAreaEntities());


        informRepository.save(informEntity);
        return informMapper.toInformDto(informRepository.save(informEntity));
    }

    @Override
    public InformDto changeGlobalState(Long id,String state) {
        InformEntity informState= informRepository.findById(id).orElseThrow();
        informState.setStatus(state);
        informRepository.save(informState);
        return informMapper.toInformDto(informState);
    }

    @Override
    public void deleteInform(Long id) {
        informRepository.deleteById(id);
    }
}
