package org.fesc.sicier.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fesc.sicier.services.auth.AuthCreateUserRequest;
import org.fesc.sicier.services.auth.AuthLoginRequest;
import org.fesc.sicier.services.auth.AuthResponse;
import org.fesc.sicier.services.dtos.response.UserDto;
import org.fesc.sicier.services.implementations.UserDetailServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sicier/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailServiceImpl userDetailService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest loginRequest) {
        return new ResponseEntity<>(userDetailService.loginUser(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/sign")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest createUserRequest) {
        return new ResponseEntity<>(userDetailService.createUser(createUserRequest), HttpStatus.CREATED);
    }

    @PostMapping("/update/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthResponse> updateUser(@RequestBody @Valid AuthCreateUserRequest createUserRequest, @PathVariable Long id) {
        return new ResponseEntity<>(userDetailService.updateUser(createUserRequest,id), HttpStatus.OK);
    }

    @GetMapping("list/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getUsersList(){
        return new ResponseEntity<>(userDetailService.findAllUsers(),HttpStatus.OK);
    }
    @GetMapping("find/user/loged")
    public ResponseEntity<UserDto> getUserLogin(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(userDetailService.findCurrentUser(authentication),HttpStatus.OK);
    }
}
