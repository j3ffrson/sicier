package org.fesc.sicier.controllers;

import lombok.RequiredArgsConstructor;
import org.fesc.sicier.services.AuthService;
import org.fesc.sicier.services.RequestService;
import org.fesc.sicier.services.dtos.request.CreateAreaReqRequest;
import org.fesc.sicier.services.dtos.request.CreateUserReqRequest;
import org.fesc.sicier.services.dtos.response.RequestResponseDto;
import org.fesc.sicier.services.dtos.response.ResponseRequestDto;
import org.fesc.sicier.services.implementations.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/sicier/api/v1/request/")
@RequiredArgsConstructor
public class RequestController {

    private final AuthService authService;
    private final RequestService requestService;

    @PostMapping("area")
    public ResponseEntity<Void> requestToArea(@RequestBody CreateAreaReqRequest areaRequest, Authentication auth) throws BusinessException {

        requestService.createReqArea(areaRequest,authService.getCurrentUser(auth));
        return ResponseEntity.ok().build();

    }
    @PostMapping("user")
    public ResponseEntity<Void> requestToArea(@RequestBody CreateUserReqRequest userRequest, Authentication auth) throws BusinessException {

        requestService.createReqUser(userRequest,authService.getCurrentUser(auth));
        return ResponseEntity.ok().build();

    }
    @PostMapping("reply/{id}")
    public ResponseEntity<Void> replyResponse(@PathVariable Long id, @RequestBody RequestResponseDto requestResponseDto,Authentication auth) throws BusinessException, AccessDeniedException {
        requestService.responseSolicited(id,requestResponseDto,authService.getCurrentUser(auth));
        return ResponseEntity.ok().build();
    }


}
