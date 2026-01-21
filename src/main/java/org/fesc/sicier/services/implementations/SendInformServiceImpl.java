package org.fesc.sicier.services.implementations;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fesc.sicier.persistence.entities.DestinationInformEntity;
import org.fesc.sicier.persistence.entities.InformEntity;
import org.fesc.sicier.persistence.entities.InformStates;
import org.fesc.sicier.persistence.entities.AreaEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.repositories.AreaRepository;
import org.fesc.sicier.persistence.repositories.DestinationInformRepository;
import org.fesc.sicier.persistence.repositories.InformRepository;
import org.fesc.sicier.persistence.repositories.UserRepository;
import org.fesc.sicier.services.NotificationService;
import org.fesc.sicier.services.SendInformService;
import org.fesc.sicier.services.StateDestination;
import org.fesc.sicier.services.dtos.response.events.ReceibedInformEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class SendInformServiceImpl implements SendInformService {

    private final InformRepository informRepository;
    private final AreaRepository areaRepository;
    private final UserRepository userRepository;
    private final DestinationInformRepository destinationInformRepository;
    private final NotificationService notificationService;

    @Override
    public void sendInformArea(Long informId, List<Long> areasDestinationIds, UserEntity user) throws BusinessException{

        InformEntity inform= informRepository.findById(informId).orElseThrow();

        validateSend(inform,user);
        inform.setStatus(InformStates.ENVIADO.name());

        for (Long areaId:areasDestinationIds){
            AreaEntity area= areaRepository.findById(areaId).orElseThrow();
            createDestinationArea(inform,area);
            log.info("Informe con id "+informId+" enviado a areas  "+area.getName()+" por "+user.getFirstName());
        }


    }

    @Override
    public void sendInformUser(Long informId, List<Long> users, UserEntity user) throws BusinessException{

        InformEntity inform= informRepository.findById(informId).orElseThrow();
        validateSend(inform,user);
        inform.setStatus(InformStates.ENVIADO.name());

        for (Long userId:users){
            UserEntity userDest= userRepository.findById(userId).orElseThrow();
            createDestinationUser(inform,userDest);
            log.info("Informe con id "+informId+" enviado a usuarios "+userDest.getFirstName()+" ,");
        }


    }

    @Override
    public void validateSend(InformEntity inform, UserEntity user) throws BusinessException {
        if(!inform.getUserEmisor().equals(user)){
            throw new BusinessException("No es el usuario");
        }
        if(!inform.getStatus().equals(InformStates.COMPLETADO.name())){
            throw new BusinessException("Estado invallido");
        }
    }

    @Override
    public void createDestinationArea(InformEntity inform, AreaEntity area) {
        DestinationInformEntity destinationInform= DestinationInformEntity.builder()
                .inform(inform)
                .areaDestination(area)
                .dateReading(LocalDateTime.now())
                .receptionDate(LocalDateTime.now())
                .status(StateDestination.RECIBIDO.name())
                .build();

        destinationInformRepository.save(destinationInform);
        notificationService.notificateArea(
                area.getId(),
                new ReceibedInformEvent(
                        inform.getId(),
                        inform.getTitle(),
                        "Area de"+inform.getAreaEmisor().getName(),
                        inform.getCreationDate()
                ));
    }
    @Override
    public void createDestinationUser(InformEntity inform, UserEntity user) {
        DestinationInformEntity destinationInform= DestinationInformEntity.builder()
                .inform(inform)
                .userDestination(user)
                .dateReading(LocalDateTime.now())
                .receptionDate(LocalDateTime.now())
                .status(StateDestination.RECIBIDO.name())
                .build();

        destinationInformRepository.save(destinationInform);
        notificationService.notificateUser(
                user.getId(),
                new ReceibedInformEvent(
                        inform.getId(),
                        inform.getTitle(),
                        "Usuario "+inform.getUserEmisor().getFirstName()+" "+inform.getUserEmisor().getLastName(),
                        inform.getCreationDate()
                ));
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
        if (!destinationInform.getAreaDestination().equals(user.getArea())){
            throw new BusinessException("no pertene al area");
        }
    }
}
