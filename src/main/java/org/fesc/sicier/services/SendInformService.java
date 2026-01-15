package org.fesc.sicier.services;

import org.fesc.sicier.persistence.entities.DestinationInformEntity;
import org.fesc.sicier.persistence.entities.InformEntity;
import org.fesc.sicier.persistence.entities.security.AreaEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.services.implementations.BusinessException;

import java.util.List;

public interface SendInformService {
    void sendInform(Long informId, List<Long> areasDestinationIds, UserEntity user) throws BusinessException;
    void validateSend(InformEntity inform,UserEntity user) throws BusinessException;
    void createDestination(InformEntity inform, AreaEntity area) throws BusinessException;
    void readInform(Long destinationId,UserEntity user) throws BusinessException;
    void validateAreaAcces(DestinationInformEntity destinationInform,UserEntity user) throws BusinessException;
}
