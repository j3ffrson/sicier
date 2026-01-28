package org.fesc.sicier.services.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.RequestEntity;
import org.fesc.sicier.persistence.entities.RequestStates;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.repositories.AreaRepository;
import org.fesc.sicier.persistence.repositories.RequestRepository;
import org.fesc.sicier.persistence.repositories.UserRepository;
import org.fesc.sicier.services.AuthService;
import org.fesc.sicier.services.NotificationService;
import org.fesc.sicier.services.RequestService;
import org.fesc.sicier.services.dtos.request.CreateAreaReqRequest;
import org.fesc.sicier.services.dtos.request.CreateUserReqRequest;
import org.fesc.sicier.services.dtos.response.RequestResponseDto;
import org.fesc.sicier.services.dtos.response.ResponseRequestDto;
import org.fesc.sicier.services.dtos.response.events.RequestReceivedEvent;
import org.fesc.sicier.services.dtos.response.events.RequestResponseEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final AreaRepository areaRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;
    private final HistoryStateService historyStateService;
    private final AuthService authService;

    @Override
    public Page<ResponseRequestDto> listRequestByUser(Pageable pageable, Authentication auth) {

        List<RequestEntity> requests= requestRepository.findRequestEntitiesByRequester(authService.getCurrentUser(auth));
        List<ResponseRequestDto>dtoList= requests.stream().map(req->{
            ResponseRequestDto dto= new ResponseRequestDto();
            dto.setTitle(req.getTitle());
            dto.setDescription(req.getDescription());
            dto.setState(req.getState().name());
            dto.setRequester(req.getRequester().getFirstName());
            dto.setCreationDate(req.getCreationDate());
            return dto;
        }).toList();

        return new PageImpl<>(dtoList);
    }

    @Override
    public Page<ResponseRequestDto> listRequestByUserDestnation(Pageable pageable, Authentication auth) {
        List<RequestEntity> requests= requestRepository.findRequestEntitiesByUserDestination(authService.getCurrentUser(auth));
        List<ResponseRequestDto>dtoList= requests.stream().map(req->{
            ResponseRequestDto dto= new ResponseRequestDto();
            dto.setTitle(req.getTitle());
            dto.setDescription(req.getDescription());
            dto.setState(req.getState().name());
            dto.setRequester(req.getRequester().getFirstName());
            dto.setCreationDate(req.getCreationDate());
            return dto;
        }).toList();

        return new PageImpl<>(dtoList);
    }

    @Override
    public void validateDestination(RequestEntity request) throws BusinessException {
        boolean area = request.getAreaDestination() != null;
        boolean usuario = request.getUserDestination() != null;

        if (area == usuario) {
            throw new BusinessException(
                    "La solicitud debe tener un destino: área o usuario"
            );
        }
    }

    @Override
    @Transactional
    public void createReqArea(CreateAreaReqRequest createAreaReqRequest, UserEntity user) throws BusinessException {

        RequestEntity request= new RequestEntity();

        request.setTitle(createAreaReqRequest.title());
        request.setDescription(createAreaReqRequest.description());
        request.setRequester(user);
        request.setAreaDestination(areaRepository.findById(createAreaReqRequest.areaDestId()).orElseThrow());

        request.setState(RequestStates.CREADA);
        request.setCreationDate(LocalDateTime.now());

        validateDestination(request);
        requestRepository.save(request);

        notificationService.notificateArea(
                request.getAreaDestination().getId(),
                new RequestReceivedEvent(
                        request.getId(),
                        request.getTitle(),
                        request.getDescription(),
                        user.getFirstName(),
                        request.getCreationDate()
                ));
        historyStateService.registerRequestState(request,RequestStates.CREADA,user,"Registro de Peticion de Areas");
    }

    @Override
    @Transactional
    public void createReqUser(CreateUserReqRequest createUserReqRequest, UserEntity user) throws BusinessException {
        RequestEntity request= new RequestEntity();

        request.setTitle(createUserReqRequest.title());
        request.setDescription(createUserReqRequest.description());
        request.setRequester(user);
        request.setUserDestination(userRepository.findById(createUserReqRequest.userDestId()).orElseThrow());

        request.setState(RequestStates.CREADA);
        request.setCreationDate(LocalDateTime.now());

        validateDestination(request);
        requestRepository.save(request);

        notificationService.notificateUser(request.getUserDestination().getUsername(),
                new RequestReceivedEvent(
                        request.getId(),
                        request.getTitle(),
                        request.getDescription(),
                        user.getFirstName(),
                        request.getCreationDate()
                ));
        historyStateService.registerRequestState(request,RequestStates.CREADA,user,"Registro de Peticion de Usuarios");
    }

    @Override
    @Transactional
    public void responseSolicited(Long id, RequestResponseDto requestResponseDto, UserEntity user) throws  AccessDeniedException {

        RequestEntity request= requestRepository.findById(id).orElseThrow();

        validarAcceso(request,user);

        request.setResponse(requestResponseDto.getResponse());
        request.setState(requestResponseDto.getNewState());
        request.setResponseDate(LocalDateTime.now());

        notificationService.notificateUser(request.getUserDestination().getUsername(),
                new RequestResponseEvent(
                        request.getId(),
                        request.getState().name(),
                        request.getResponse(),
                        request.getResponseDate()
                ));

    }
    private void validarAcceso(RequestEntity s, UserEntity usuario) throws AccessDeniedException {

        if (s.getUserDestination() != null &&
                !s.getUserDestination().equals(usuario)) {
            throw new AccessDeniedException("No autorizado");
        }

        if (s.getAreaDestination() != null &&
                !s.getAreaDestination().equals(usuario.getArea())) {
            throw new AccessDeniedException("No autorizado");
        }
    }

}
