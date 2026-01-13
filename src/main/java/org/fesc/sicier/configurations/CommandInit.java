package org.fesc.sicier.configurations;


import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.security.*;
import org.fesc.sicier.persistence.repositories.AreaRepository;
import org.fesc.sicier.persistence.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CommandInit {

    @Bean
    CommandLineRunner init(PasswordEncoder passwordEncoder, UserRepository userRepository, AreaRepository areaRepository){
        return args -> {
            PermissionEntity created= PermissionEntity.builder().name("CREATE").build();
            PermissionEntity read= PermissionEntity.builder().name("READ").build();
            PermissionEntity update= PermissionEntity.builder().name("UPDATE").build();
            PermissionEntity delete= PermissionEntity.builder().name("DELETE").build();

            RoleEntity admin= RoleEntity.builder()
                    .roles(Roles.ADMIN)
                    .listaPermisos(Set.of(created,read,update,delete))
                    .build();
            RoleEntity medic= RoleEntity.builder()
                    .roles(Roles.EMISOR)
                    .listaPermisos(Set.of(created,read,update))
                    .build();
            RoleEntity enf= RoleEntity.builder()
                    .roles(Roles.REVISOR)
                    .listaPermisos(Set.of(created,read))
                    .build();


            UserEntity user= UserEntity.builder()
                    .firstName("Jefferson")
                    .lastName("Chaustre")
                    .username("jeffer")
                    .identifer(1092524589L)
                    .phone(3166846822L)
                    .institutionalEmail("chaustrejefferson@gmail.com")
                    .password(passwordEncoder.encode("milluh123"))
                    .roles(Set.of(admin,medic,enf))
                    .isEnabled(true)
                    .isAccountNonExpired(true)
                    .isCredentialsNonExpired(true)
                    .isAccountNonLocked(true)
                    .build();

            AreaEntity area= AreaEntity.builder()
                    .name("Resctoria")
                    .Active(true)
                    .users(Collections.emptyList())
                    .description("Area de Rectoria")
                    .build();
            areaRepository.save(area);
            user.setAreaEntities(area);
            userRepository.save(user);
            area.setUsers(List.of(user));
            areaRepository.save(area);
        };
    }

}
