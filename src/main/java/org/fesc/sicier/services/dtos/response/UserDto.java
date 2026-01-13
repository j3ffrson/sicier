package org.fesc.sicier.services.dtos.response;

import lombok.Data;

@Data
public class UserDto {


    private String firstName;
    private String lastName;
    private Long phone;
    private Long identifier;
    private String username;
    private String institutionalEmail;
    private AreaDto areaEntities;

}
