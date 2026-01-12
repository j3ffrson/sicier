package org.fesc.sicier.services.implementations;

import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity usuario= userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        List<SimpleGrantedAuthority> authorityList= new ArrayList<>();

        usuario.getRoles().forEach(role->
                authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoles().name()))));

        usuario.getRoles().stream()
                .flatMap(role->role.getListaPermisos().stream())
                .forEach(permission->authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(usuario.getUsername(),usuario.getPassword(),authorityList);

    }
}
