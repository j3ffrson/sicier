package org.fesc.sicier.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fesc.sicier.services.AreaService;
import org.fesc.sicier.services.dtos.request.CreateAreaRequest;
import org.fesc.sicier.services.dtos.response.AreaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sicier/api/v1/area/")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @GetMapping("list")
    public ResponseEntity<Page<AreaDto>> getListAreas(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                                      @RequestParam(name = "size",defaultValue = "3") int pageSize){
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        return new ResponseEntity<>(areaService.getAllAreas(pageable),HttpStatus.OK);

    }

    @GetMapping("id/{id}")
    public ResponseEntity<AreaDto> getAreaById(@PathVariable Long id){
        return new ResponseEntity<>(areaService.getAreaById(id),HttpStatus.OK);
    }
    @GetMapping("name/{name}")
    public ResponseEntity<AreaDto> getAreaByName(@PathVariable String name){
        return new ResponseEntity<>(areaService.getAreaByName(name),HttpStatus.OK);
    }

    @PostMapping("new")
    public ResponseEntity<AreaDto> createArea(@RequestBody @Valid CreateAreaRequest createAreaRequest){
        return new ResponseEntity<>(areaService.createArea(createAreaRequest), HttpStatus.CREATED);
    }
    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('RECTOR')")
    public ResponseEntity<AreaDto> updateArea(@PathVariable @Valid Long id,@RequestBody CreateAreaRequest createAreaRequest){
        return new ResponseEntity<>(areaService.updateArea(createAreaRequest,id),HttpStatus.OK);
    }

}
