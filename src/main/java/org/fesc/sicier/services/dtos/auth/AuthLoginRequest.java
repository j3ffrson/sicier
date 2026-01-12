package org.fesc.sicier.services.dtos.auth;

import jakarta.validation.constraints.NotBlank;

/*
* dto para la introduccion de credenciales en el inicio de sesion
* con el metodo Post
* */
public record AuthLoginRequest(
        @NotBlank String username,
        @NotBlank String password

){}
