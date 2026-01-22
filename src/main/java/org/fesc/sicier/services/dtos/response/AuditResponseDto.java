package org.fesc.sicier.services.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AuditResponseDto(
        String objectName,
        String operation,
        String username,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime date
) {
}
