package org.fesc.sicier.services.implementations;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.fesc.sicier.persistence.entities.DestinationInformEntity;
import org.fesc.sicier.persistence.entities.InformEntity;
import org.fesc.sicier.persistence.entities.InformStates;
import org.fesc.sicier.persistence.entities.security.AreaEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.repositories.AreaRepository;
import org.fesc.sicier.persistence.repositories.DestinationInformRepository;
import org.fesc.sicier.persistence.repositories.InformRepository;
import org.fesc.sicier.services.SendInformService;
import org.fesc.sicier.services.StateDestination;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class SendInformServiceImpl implements SendInformService {

    private final InformRepository informRepository;
    private final AreaRepository areaRepository;
    private final DestinationInformRepository destinationInformRepository;

    @Override
    public void sendInform(Long informId, List<Long> areasDestinationIds, UserEntity user) throws BusinessException{

        InformEntity inform= informRepository.findById(informId).orElseThrow();
        validateSend(inform,user);
        inform.setStatus(InformStates.ENVIADO.name());

        for (Long areaId:areasDestinationIds){
            AreaEntity area= areaRepository.findById(areaId).orElseThrow();

            createDestination(inform,area);

        }

    }

    @Override
    public void validateSend(InformEntity inform, UserEntity user) throws BusinessException {
        if(!inform.getUserEmisor().equals(user)){
            throw new BusinessException("No es el usuario");
        }
        if(inform.getStatus()!=InformStates.COMPLETADO.name()){
            throw new BusinessException("Estado invallido");
        }
    }

    @Override
    public void createDestination(InformEntity inform, AreaEntity area) {
        DestinationInformEntity destinationInform= DestinationInformEntity.builder()
                .inform(inform)
                .areaDestination(area)
                .dateReading(LocalDateTime.now())
                .receptionDate(LocalDateTime.now())
                .status(StateDestination.RECIBIDO.name())
                .build();

        destinationInformRepository.save(destinationInform);
    }

    @Override
    @Transactional
    public void readInform(Long destinationId, UserEntity user) throws BusinessException {
        DestinationInformEntity destinationInform= destinationInformRepository.findById(destinationId).orElseThrow();
        validateAreaAcces(destinationInform,user);
        if(destinationInform.getStatus()==StateDestination.RECIBIDO.name()){
            destinationInform.setStatus(StateDestination.LEIDO.name());
        }
    }

    @Override
    public void validateAreaAcces(DestinationInformEntity destinationInform, UserEntity user) throws BusinessException{
        if (!destinationInform.getAreaDestination().equals(user.getAreaEntities())){
            throw new BusinessException("no pertene al area");
        }
    }
}
