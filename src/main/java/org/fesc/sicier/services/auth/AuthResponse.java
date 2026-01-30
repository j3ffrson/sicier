package org.fesc.sicier.services.auth;

import java.util.List;

public record AuthResponse(
        String username,
        String message,
        String jwt,
        List<String> roles,
        boolean status
){
}
