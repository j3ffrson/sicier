package org.fesc.sicier.services.implementations;

import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.*;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.repositories.HistoryStateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistoryStateService {

    private final HistoryStateRepository historyStateRepository;

    public void registerInformState(
            InformEntity inform,
            InformStates state,
            UserEntity user,
            String description

    ){
        HistoryStateEntity historyState= HistoryStateEntity.builder()
                .informId(inform.getId())
                .date(LocalDateTime.now())
                .state(state)
                .description(description)
                .userId(user.getId())
                .build();
        historyStateRepository.save(historyState);
    }
    public void registerRequestState(
            RequestEntity request,
            RequestStates state,
            UserEntity user,
            String description

    ){
        HistoryStateEntity historyState= HistoryStateEntity.builder()
                .requestId(request.getId())
                .date(LocalDateTime.now())
                .requestState(state)
                .description(description)
                .userId(user.getId())
                .build();


        historyStateRepository.save(historyState);
    }

}
