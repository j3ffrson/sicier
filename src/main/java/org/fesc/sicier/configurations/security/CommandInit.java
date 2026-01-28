package org.fesc.sicier.configurations.security;


import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.AreaEntity;
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
            PermissionEntity create= PermissionEntity.builder().name("CREATE").build();
            PermissionEntity read= PermissionEntity.builder().name("READ").build();
            PermissionEntity update= PermissionEntity.builder().name("UPDATE").build();
            PermissionEntity delete= PermissionEntity.builder().name("DELETE").build();

            RoleEntity admin= RoleEntity.builder()
                    .rolesName(ERoles.ADMIN)
                    .listPermission(Set.of(create,read,update,delete))
                    .build();
            RoleEntity director= RoleEntity.builder()
                    .rolesName(ERoles.RECTOR)
                    .listPermission(Set.of(create,read,update))
                    .build();
            RoleEntity rector= RoleEntity.builder()
                    .rolesName(ERoles.RECTOR)
                    .listPermission(Set.of(create,read,update))
                    .build();
            RoleEntity funcionario= RoleEntity.builder()
                    .rolesName(ERoles.FUNC)
                    .listPermission(Set.of(create,read,update))
                    .build();

            AreaEntity dAcademico= AreaEntity.builder()
                    .name("DAcademico")
                    .description("Area de Desarrollo academico")
                    .active(true)
                    .build();
            AreaEntity cartera= AreaEntity.builder()
                    .description("Area de Credito y Cartera")
                    .name("Credito y Cartera")
                    .active(true)
                    .build();
            AreaEntity bienestar= AreaEntity.builder()
                    .description("Area de Bienestar Institucional")
                    .name("Bienestar")
                    .active(true)
                    .build();
            AreaEntity rectoria= AreaEntity.builder()
                    .description("Rectoria")
                    .name("Rectoria")
                    .active(true)
                    .build();

            areaRepository.saveAll(List.of(dAcademico,cartera,bienestar,rectoria));

            UserEntity jeffer= UserEntity.builder()
                    .firstName("Jefferson")
                    .lastName("Chaustre")
                    .identifier(1092524589L)
                    .username("jeffer")
                    .phone(3166846822L)
                    .area(rectoria)
                    .roles(Set.of(director,admin))
                    .institutionalEmail("est_jr_chaustre@fesc.edu.co")
                    .password(passwordEncoder.encode("milluh123"))
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isEnabled(true)
                    .isCredentialsNonExpired(true)
                    .build();
            UserEntity brayan= UserEntity.builder()
                    .firstName("Brayam")
                    .lastName("Osoario")
                    .identifier(105489544L)
                    .username("brayan")
                    .phone(356846897L)
                    .area(dAcademico)
                    .roles(Set.of(rector))
                    .institutionalEmail("est_ba_osorio@fesc.edu.co")
                    .password(passwordEncoder.encode("brayan123"))
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isEnabled(true)
                    .isCredentialsNonExpired(true)
                    .build();
            UserEntity neira= UserEntity.builder()
                    .firstName("Juan")
                    .lastName("Neira")
                    .identifier(152456874L)
                    .username("neira")
                    .phone(784587458L)
                    .area(cartera)
                    .roles(Set.of(funcionario))
                    .institutionalEmail("est_jd_neira@fesc.edu.co")
                    .password(passwordEncoder.encode("neira1234"))
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isEnabled(true)
                    .isCredentialsNonExpired(true)
                    .build();

            userRepository.saveAll(List.of(jeffer,brayan,neira));

        };
    }

}
