package org.fesc.sicier.services.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseRequestDto {

    private String title;
    private String description;
    private String state;
    private String requester;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

}
