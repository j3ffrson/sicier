package org.fesc.sicier.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fesc.sicier.services.InformServices;
import org.fesc.sicier.services.dtos.request.CreateInformRequest;
import org.fesc.sicier.services.dtos.response.InformDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sicier/api/v1/inform/")
@RequiredArgsConstructor
public class InformController {

    private final InformServices informServices;

    @GetMapping("list")
    public ResponseEntity<List<InformDto>> getListInform(){
        return new ResponseEntity<>(informServices.getAllInform(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<InformDto> getInformById(@PathVariable Long id){
        return new ResponseEntity<>(informServices.getInformById(id),HttpStatus.OK);
    }
    @PostMapping("new")
    public ResponseEntity<InformDto> creteInform(){
        return new ResponseEntity<>(informServices.createInformDraft(),HttpStatus.CREATED);
    }
    @PutMapping("created/{id}")
    public ResponseEntity<InformDto> creteInformComplete(@RequestBody @Valid CreateInformRequest createInformRequest,@PathVariable Long id){
        return new ResponseEntity<>(informServices.CreateInformComplete(createInformRequest,id),HttpStatus.OK);
    }

}
