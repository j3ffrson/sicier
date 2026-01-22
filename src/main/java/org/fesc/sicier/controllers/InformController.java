package org.fesc.sicier.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.InformStates;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.repositories.UserRepository;
import org.fesc.sicier.services.AuthService;
import org.fesc.sicier.services.InformServices;
import org.fesc.sicier.services.StateDestination;
import org.fesc.sicier.services.dtos.request.CreateInformRequest;
import org.fesc.sicier.services.dtos.response.InformDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sicier/api/v1/inform/")
@RequiredArgsConstructor
public class InformController {

    private final InformServices informServices;
    private final UserRepository userRepository;
    private final AuthService authService;

    @GetMapping("general/list")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Page<InformDto>> getListInformGlobal(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                                         @RequestParam(name = "size",defaultValue = "3") int pageSize){

        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        return new ResponseEntity<>(informServices.getAllInform(pageable),HttpStatus.OK);

    }
    @GetMapping("user/list")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Page<InformDto>> getListInformUser(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                                         @RequestParam(name = "size",defaultValue = "3") int pageSize){


        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user= userRepository.findByUsername(username).orElseThrow();

        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        return new ResponseEntity<>(informServices.getAllInformByUser(user.getId(),pageable),HttpStatus.OK);

    }
    @GetMapping("area")
    public ResponseEntity<List<InformDto>> getInformByStateAndArea(@RequestParam StateDestination state, Authentication auth){
        return new ResponseEntity<>(informServices.bandejaAreaPorEstado(authService.getCurrentUser(auth),state),HttpStatus.OK);
    }
    @GetMapping("user")
    public ResponseEntity<List<InformDto>> getInformByStateAndUser(@RequestParam StateDestination state, Authentication auth){
        return new ResponseEntity<>(informServices.bandejaUserPorEstado(authService.getCurrentUser(auth),state),HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<InformDto> getInformById(@PathVariable Long id){
        return new ResponseEntity<>(informServices.getInformById(id),HttpStatus.OK);
    }
    @GetMapping("{title}")
    public ResponseEntity<InformDto> getInformByTitle(@PathVariable String title){
        return new ResponseEntity<>(informServices.getInformByTitle(title),HttpStatus.OK);
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
