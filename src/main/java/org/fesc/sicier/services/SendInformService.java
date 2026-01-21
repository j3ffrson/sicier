package org.fesc.sicier.services;

import org.fesc.sicier.persistence.entities.DestinationInformEntity;
import org.fesc.sicier.persistence.entities.InformEntity;
import org.fesc.sicier.persistence.entities.AreaEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.services.implementations.BusinessException;

import java.util.List;

public interface SendInformService {
    void sendInformArea(Long informId, List<Long> areasDestinationIds, UserEntity user) throws BusinessException;
    void sendInformUser(Long informId, List<Long> areasDestinationIds, UserEntity user) throws BusinessException;
    void validateSend(InformEntity inform,UserEntity user) throws BusinessException;
    void createDestinationArea(InformEntity inform, AreaEntity area) throws BusinessException;
    void createDestinationUser(InformEntity inform, UserEntity user) throws BusinessException;
    void readInform(Long destinationId,UserEntity user) throws BusinessException;
    void validateAreaAcces(DestinationInformEntity destinationInform,UserEntity user) throws BusinessException;
}
