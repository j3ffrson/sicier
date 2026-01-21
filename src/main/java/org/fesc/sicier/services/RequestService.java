package org.fesc.sicier.services;

import org.fesc.sicier.persistence.entities.RequestEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.services.dtos.request.CreateAreaReqRequest;
import org.fesc.sicier.services.dtos.request.CreateUserReqRequest;
import org.fesc.sicier.services.dtos.response.RequestResponseDto;
import org.fesc.sicier.services.dtos.response.ResponseRequestDto;
import org.fesc.sicier.services.implementations.BusinessException;
import org.springframework.security.core.Authentication;

import java.nio.file.AccessDeniedException;

public interface RequestService {
    void validateDestination(RequestEntity request) throws BusinessException;
    void createReqArea(CreateAreaReqRequest createAreaReqRequest, UserEntity user) throws BusinessException;
    void createReqUser(CreateUserReqRequest createUserReqRequest, UserEntity user) throws BusinessException;
    void responseSolicited(Long id, RequestResponseDto requestResponseDto, UserEntity user) throws BusinessException, AccessDeniedException;
}
