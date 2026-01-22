package org.fesc.sicier.services.interfaces;

import org.fesc.sicier.services.dtos.response.AuditResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface AuditService {
    List<AuditResponseDto> getAll();
    AuditResponseDto getById(Long id);
    List<AuditResponseDto> getByOperation(String operation);
    List<AuditResponseDto> getByDate(LocalDate date);
}
