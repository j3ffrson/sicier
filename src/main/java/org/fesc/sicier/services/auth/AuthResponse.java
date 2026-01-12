package org.fesc.sicier.services.auth;

public record AuthResponse(
        String username,
        String message,
        String jwt,
        boolean status
){
}
