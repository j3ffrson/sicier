package org.fesc.sicier.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fesc.sicier.services.InformServices;
import org.fesc.sicier.services.dtos.request.CreateInformRequest;
import org.fesc.sicier.services.dtos.response.InformDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sicier/api/v1/inform/")
@RequiredArgsConstructor
public class InformController {

    private final InformServices informServices;

    @GetMapping("list")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Page<InformDto>> getListInform(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                                         @RequestParam(name = "size",defaultValue = "3") int pageSize){

        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        return new ResponseEntity<>(informServices.getAllInform(pageable),HttpStatus.OK);

    }
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<InformDto> getInformById(@PathVariable Long id){
        return new ResponseEntity<>(informServices.getInformById(id),HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('CREATE')")
    @PostMapping("new")
    public ResponseEntity<InformDto> creteInformDraft(){
        return new ResponseEntity<>(informServices.createInformDraft(),HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('UPDATE')")
    @PutMapping("created/{id}")
    public ResponseEntity<InformDto> creteInformComplete(@RequestBody @Valid CreateInformRequest createInformRequest,@PathVariable Long id){
        return new ResponseEntity<>(informServices.createInformComplete(createInformRequest,id),HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> deleteInform(@PathVariable Long id){
        informServices.deleteInform(id);
        return new ResponseEntity<>("Inform deleted",HttpStatus.OK);
    }

}
