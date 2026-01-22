package org.fesc.sicier.services.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record HistoryStateResponseDto(
        Long informId,
        Long requestId,
        String state,
        String requestState,
        Long userId,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime date,
        String description
) {
}
