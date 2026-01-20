package org.fesc.sicier.services.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseRequestDto {

    private Long id;
    private String title;
    private String state;
    private String requester;
    private LocalDateTime creationDate;

}
