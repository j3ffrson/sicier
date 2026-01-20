package org.fesc.sicier.controllers;

import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.services.AuthService;
import org.fesc.sicier.services.SendInformService;
import org.fesc.sicier.services.dtos.request.SendInformRequest;
import org.fesc.sicier.services.implementations.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sicier/api/v1/send")
@RequiredArgsConstructor
public class SendInformController {

    private final SendInformService sendInformService;
    private final AuthService authService;

    @PostMapping("/inform/{id}/area")
    public ResponseEntity<Void> sendInformArea(
            @PathVariable Long id,
            @RequestBody SendInformRequest request,
            Authentication authentication) throws BusinessException {

        UserEntity user = authService.getCurrentUser(authentication);

        sendInformService.sendInformArea(
                id,
                request.destinations(),
                user
        );

        return ResponseEntity.ok().build();
    }
    @PostMapping("/inform/{id}/user")
    public ResponseEntity<Void> sendInformUser(
            @PathVariable Long id,
            @RequestBody SendInformRequest request,
            Authentication authentication) throws BusinessException {

        UserEntity user = authService.getCurrentUser(authentication);

        sendInformService.sendInformUser(
                id,
                request.destinations(),
                user
        );

        return ResponseEntity.ok().build();
    }

}
