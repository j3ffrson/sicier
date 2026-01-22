package org.fesc.sicier.services.implementations;

import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.*;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.repositories.HistoryStateRepository;
import org.fesc.sicier.services.dtos.response.HistoryStateResponseDto;
import org.fesc.sicier.services.interfaces.IHistoryStateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryStateService implements IHistoryStateService {

    private final HistoryStateRepository historyStateRepository;

    @Override
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

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<HistoryStateResponseDto> getAll() {
        return historyStateRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public HistoryStateResponseDto getById(Long id) {
        return historyStateRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("History state not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoryStateResponseDto> getByInformId(Long informId) {
        return historyStateRepository.findByInformId(informId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoryStateResponseDto> getByRequestId(Long requestId) {
        return historyStateRepository.findByRequestId(requestId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoryStateResponseDto> getByInformState(InformStates state) {
        return historyStateRepository.findByState(state).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoryStateResponseDto> getByRequestState(RequestStates state) {
        return historyStateRepository.findByRequestState(state).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private HistoryStateResponseDto mapToDto(HistoryStateEntity entity) {
        return new HistoryStateResponseDto(
                entity.getInformId(),
                entity.getRequestId(),
                entity.getState() != null ? entity.getState().name() : null,
                entity.getRequestState() != null ? entity.getRequestState().name() : null,
                entity.getUserId(),
                entity.getDate(),
                entity.getDescription()
        );
    }
}
