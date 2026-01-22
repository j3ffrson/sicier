package org.fesc.sicier.services.interfaces;

import org.fesc.sicier.persistence.entities.InformEntity;
import org.fesc.sicier.persistence.entities.InformStates;
import org.fesc.sicier.persistence.entities.RequestEntity;
import org.fesc.sicier.persistence.entities.RequestStates;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.services.dtos.response.HistoryStateResponseDto;

import java.util.List;

public interface IHistoryStateService {
    void registerInformState(InformEntity inform, InformStates state, UserEntity user, String description);
    void registerRequestState(RequestEntity request, RequestStates state, UserEntity user, String description);
    
    List<HistoryStateResponseDto> getAll();
    HistoryStateResponseDto getById(Long id);
    List<HistoryStateResponseDto> getByInformId(Long informId);
    List<HistoryStateResponseDto> getByRequestId(Long requestId);
    List<HistoryStateResponseDto> getByInformState(InformStates state);
    List<HistoryStateResponseDto> getByRequestState(RequestStates state);
}
