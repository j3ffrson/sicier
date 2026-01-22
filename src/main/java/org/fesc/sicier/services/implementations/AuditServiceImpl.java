package org.fesc.sicier.services.implementations;

import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.AuditEntity;
import org.fesc.sicier.persistence.repositories.AuditRepository;
import org.fesc.sicier.services.dtos.response.AuditResponseDto;
import org.fesc.sicier.services.interfaces.AuditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AuditResponseDto> getAll() {
        return auditRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AuditResponseDto getById(Long id) {
        return auditRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Audit record not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditResponseDto> getByOperation(String operation) {
        return auditRepository.findByOperation(operation).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditResponseDto> getByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return auditRepository.findByDateBetween(startOfDay, endOfDay).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private AuditResponseDto mapToDto(AuditEntity entity) {
        return new AuditResponseDto(
                entity.getObjectName(),
                entity.getOperation(),
                entity.getUsername(),
                entity.getDate()
        );
    }
}
