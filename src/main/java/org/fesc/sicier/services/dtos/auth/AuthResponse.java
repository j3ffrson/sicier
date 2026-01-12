package org.fesc.sicier.services.dtos.auth;

public record AuthResponse(
        String username,
        String message,
        String jwt,
        boolean status
){
}
