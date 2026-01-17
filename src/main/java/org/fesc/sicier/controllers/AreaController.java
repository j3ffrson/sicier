package org.fesc.sicier.controllers;

import lombok.RequiredArgsConstructor;
import org.fesc.sicier.services.AreaService;
import org.fesc.sicier.services.dtos.request.CreateAreaRequest;
import org.fesc.sicier.services.dtos.response.AreaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sicier/api/v1/area/")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @PostMapping("new")
    public ResponseEntity<AreaDto> createArea(@RequestBody CreateAreaRequest createAreaRequest){
        return new ResponseEntity<>(areaService.createArea(createAreaRequest), HttpStatus.CREATED);
    }

}
