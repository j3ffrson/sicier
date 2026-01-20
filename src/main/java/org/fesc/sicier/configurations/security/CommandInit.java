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
                    .listPermission(Set.of(created,read,update))
                    .build();

            roleRepository.saveAll(List.of(admin,rector,funcionario));

        };
    }

}
