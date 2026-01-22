package org.fesc.sicier.persistence.listener;


import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.AuditEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.repositories.AuditRepository;
import org.fesc.sicier.services.AuthService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuditEntityListener {

    private final AuditRepository auditRepository;
    private final AuthService authService;

    @PrePersist
    void prePersist(Object entity){

        List<String> entityName = Arrays.stream(entity.getClass().getName().split("\\.")).toList();
        String user = "SYSTEM";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) user= auth.getName();
        history(entityName.getLast(), "INSERT", user);
    }
    @PreUpdate
    void preUpdate(Object entity){
        List<String> entityName= Arrays.stream(entity.getClass().getName().split("\\.")).toList();
        String user = "SYSTEM";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) user= auth.getName();
        history(entityName.getLast(),"UPDATE",user);
    }
    @PreRemove
    void preRemove(Object entity){
        List<String> entityName= Arrays.stream(entity.getClass().getName().split("\\.")).toList();
        String user = "SYSTEM";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) user= auth.getName();
        history(entityName.getLast(),"DELETE",user);
    }

    private void history(String object,String operation,String username){
        AuditEntity historyChanges = new AuditEntity();
        historyChanges.setObjectName(object);
        historyChanges.setOperation(operation);
        historyChanges.setUsername(username);
        historyChanges.setDate(LocalDateTime.now());
        auditRepository.save(historyChanges);
    }
}
