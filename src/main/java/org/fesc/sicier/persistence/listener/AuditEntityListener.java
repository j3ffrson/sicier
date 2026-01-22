package org.fesc.sicier.persistence.listener;


import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.AuditEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.repositories.AuditRepository;
import org.fesc.sicier.persistence.repositories.UserRepository;
import org.fesc.sicier.services.AuthService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuditEntityListener {

    private final AuditRepository auditRepository;
    private final UserRepository userRepository;

    @PrePersist
    void prePersist(Object entity){

        List<String> entityName = Arrays.stream(entity.getClass().getName().split("\\.")).toList();
        String user = getCurrentUser();
        history(entityName.getLast(), "INSERT", user);
    }
    @PreUpdate
    void preUpdate(Object entity){
        List<String> entityName= Arrays.stream(entity.getClass().getName().split("\\.")).toList();
        String user = getCurrentUser();
        history(entityName.getLast(),"UPDATE",user);
    }
    @PreRemove
    void preRemove(Object entity){
        List<String> entityName= Arrays.stream(entity.getClass().getName().split("\\.")).toList();
        String user = getCurrentUser();
        history(entityName.getLast(),"DELETE",user);
    }

    private String getCurrentUser(){
        String user = "SYSTEM";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()){
            UserEntity userEntity= userRepository.findByUsername(auth.getName()).orElseThrow(()->new UsernameNotFoundException(auth.getName()));
            user= String.format("%s %s",userEntity.getFirstName(),userEntity.getLastName());
        }

        return user;
    }

    private void history(String object,String operation,String username){
        AuditEntity historyChanges = new AuditEntity();
        historyChanges.setObjectName(object);
        historyChanges.setOperation(operation);
        historyChanges.setUser(username);
        historyChanges.setDate(LocalDateTime.now());
        auditRepository.save(historyChanges);
    }
}
