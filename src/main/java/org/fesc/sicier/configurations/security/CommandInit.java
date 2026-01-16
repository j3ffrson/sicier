package org.fesc.sicier.configurations.security;


import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.security.*;
import org.fesc.sicier.persistence.repositories.AreaRepository;
import org.fesc.sicier.persistence.repositories.RoleRepository;
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
    CommandLineRunner init(PasswordEncoder passwordEncoder, UserRepository userRepository, AreaRepository areaRepository, RoleRepository roleRepository){
        return args -> {
            PermissionEntity created= PermissionEntity.builder().name("CREATE").build();
            PermissionEntity read= PermissionEntity.builder().name("READ").build();
            PermissionEntity update= PermissionEntity.builder().name("UPDATE").build();
            PermissionEntity delete= PermissionEntity.builder().name("DELETE").build();

            RoleEntity admin= RoleEntity.builder()
                    .rolesName(ERoles.ADMIN)
                    .listPermission(Set.of(created,read,update,delete))
                    .build();
            RoleEntity rector= RoleEntity.builder()
                    .rolesName(ERoles.RECTOR)
                    .listPermission(Set.of(created,read,update))
                    .build();
            RoleEntity funcionario= RoleEntity.builder()
                    .rolesName(ERoles.FUNC)
                    .listPermission(Set.of(created,read))
                    .build();

            UserEntity user= UserEntity.builder()
                    .firstName("Jefferson")
                    .lastName("Chaustre")
                    .username("jeffer")
                    .identifier(1092524589L)
                    .phone(3166846822L)
                    .institutionalEmail("chaustrejefferson@gmail.com")
                    .password(passwordEncoder.encode("milluh123"))
                    .roles(Set.of(admin,rector,funcionario))
                    .isEnabled(true)
                    .isAccountNonExpired(true)
                    .isCredentialsNonExpired(true)
                    .isAccountNonLocked(true)
                    .build();
            UserEntity user2= UserEntity.builder()
                    .firstName("Brayam")
                    .lastName("Osorio")
                    .username("Brayam")
                    .identifier(3333333L)
                    .phone(222222L)
                    .institutionalEmail("Brayam@gmail.com")
                    .password(passwordEncoder.encode("1234"))
                    .roles(Set.of(admin,rector,funcionario))
                    .isEnabled(true)
                    .isAccountNonExpired(true)
                    .isCredentialsNonExpired(true)
                    .isAccountNonLocked(true)
                    .build();

            AreaEntity area= AreaEntity.builder()
                    .name("Resctoria")
                    .active(true)
                    .users(Collections.emptyList())
                    .description("Area de Rectoria")
                    .build();
            AreaEntity area2= AreaEntity.builder()
                    .name("DAcademico")
                    .active(true)
                    .users(Collections.emptyList())
                    .description("Desarrollo academico")
                    .build();

            areaRepository.saveAll(List.of(area,area2));
            user.setAreaEntities(area);
            user2.setAreaEntities(area2);
            userRepository.saveAll(List.of(user,user2));
            area.setUsers(List.of(user));
            area2.setUsers(List.of(user2));
            areaRepository.saveAll(List.of(area,area2));
        };
    }

}
