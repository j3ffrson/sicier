package org.fesc.sicier.services.dtos.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AreaDto {

    private String name;
    private String description;
    private Boolean active;
    private List<UserDto> users;
    private List<InformDto> informs;

}
