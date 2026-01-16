package org.fesc.sicier.services;

import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.springframework.security.core.Authentication;

public interface AuthService {
    UserEntity getCurrentUser(Authentication authentication);
}
