package org.fesc.sicier.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fesc.sicier.services.AuthService;
import org.fesc.sicier.services.RequestService;
import org.fesc.sicier.services.dtos.request.CreateAreaReqRequest;
import org.fesc.sicier.services.dtos.request.CreateUserReqRequest;
import org.fesc.sicier.services.dtos.response.RequestResponseDto;
import org.fesc.sicier.services.dtos.response.ResponseRequestDto;
import org.fesc.sicier.services.implementations.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/sicier/api/v1/request/")
@RequiredArgsConstructor
public class RequestController {

    private final AuthService authService;
    private final RequestService requestService;

    @GetMapping("user/list")
    public ResponseEntity<Page<ResponseRequestDto>> getListRequestByUser(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                                                         @RequestParam(name = "size",defaultValue = "3") int pageSize){

        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(requestService.listRequestByUser(pageable,auth), HttpStatus.OK);
    }

    @GetMapping("user/destination/list")
    public ResponseEntity<Page<ResponseRequestDto>> getListRequestByDestination(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                                                         @RequestParam(name = "size",defaultValue = "3") int pageSize){

        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(requestService.listRequestByUserDestnation(pageable,auth), HttpStatus.OK);
    }

    @PostMapping("area")
    public ResponseEntity<Void> requestToArea(@RequestBody @Valid CreateAreaReqRequest areaRequest, Authentication auth) throws BusinessException {

        requestService.createReqArea(areaRequest,authService.getCurrentUser(auth));
        return ResponseEntity.ok().build();

    }
    @PostMapping("user")
    public ResponseEntity<Void> requestToArea(@RequestBody @Valid CreateUserReqRequest userRequest, Authentication auth) throws BusinessException {

        requestService.createReqUser(userRequest,authService.getCurrentUser(auth));
        return ResponseEntity.ok().build();

    }
    @PostMapping("reply/{id}")
    public ResponseEntity<Void> replyResponse(@PathVariable Long id, @RequestBody @Valid RequestResponseDto requestResponseDto,Authentication auth) throws BusinessException, AccessDeniedException {
        requestService.responseSolicited(id,requestResponseDto,authService.getCurrentUser(auth));
        return ResponseEntity.ok().build();
    }


}
