package org.fesc.sicier.services.implementations;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.security.AreaEntity;
import org.fesc.sicier.persistence.entities.security.ERoles;
import org.fesc.sicier.persistence.entities.security.RoleEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.repositories.AreaRepository;
import org.fesc.sicier.persistence.repositories.RoleRepository;
import org.fesc.sicier.persistence.repositories.UserRepository;
import org.fesc.sicier.services.auth.AuthCreateUserRequest;
import org.fesc.sicier.services.auth.AuthLoginRequest;
import org.fesc.sicier.services.auth.AuthResponse;
import org.fesc.sicier.utils.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AreaRepository areaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity usuario= userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        List<SimpleGrantedAuthority> authorityList= new ArrayList<>();

        usuario.getRoles().forEach(role->
                authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolesName().name()))));

        usuario.getRoles().stream()
                .flatMap(role->role.getListPermission().stream())
                .forEach(permission->authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(usuario.getUsername(),usuario.getPassword(),authorityList);

    }

    public AuthResponse loginUser(@Valid AuthLoginRequest loginRequest){

        String username= loginRequest.username();
        String password= loginRequest.password();

        Authentication authentication= this.authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String acccesToken= jwtUtil.generateToken(authentication);

        AuthResponse authResponse= new AuthResponse(username,"user login",acccesToken,true);
        return authResponse;


    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails= this.loadUserByUsername(username);

        if(!passwordEncoder.matches(password,userDetails.getPassword()))
            throw new BadCredentialsException("Invalid password");

        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());

    }

    public AuthResponse createUser(@Valid AuthCreateUserRequest createUserRequest){

        String name= createUserRequest.firstName();
        String lastName= createUserRequest.lastName();
        String email= createUserRequest.institutionalEmail();
        Long phone= createUserRequest.phone();
        Long identifier= createUserRequest.identifier();
        String username= createUserRequest.username();
        String password= createUserRequest.password();
        String areaName= createUserRequest.area();
        List<String> roleList= createUserRequest.roleRequest().roleList();

        List<ERoles> roles = roleList.stream()
                .map(ERoles::valueOf)
                .collect(Collectors.toList());

        Set<RoleEntity> roleEntities= roleRepository.findRoleEntitiesByRolesNameIn(roles)
                .stream().collect(Collectors.toSet());
        AreaEntity areaEntity= areaRepository.findByName(areaName).orElseThrow();
        UserEntity user=UserEntity.builder()
                .firstName(name)
                .lastName(lastName)
                .username(username)
                .institutionalEmail(email)
                .phone(phone)
                .identifier(identifier)
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(roleEntities)
                .areaEntities(areaEntity)
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .build();

        UserEntity usuarioEntity= userRepository.save(user);
        List<SimpleGrantedAuthority> authorityList= new ArrayList<>();

        usuarioEntity.getRoles().stream().forEach(role->
                authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolesName().name()))));

        usuarioEntity.getRoles().stream().flatMap(role->role.getListPermission().stream())
                .forEach(permission->authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        Authentication authentication= new UsernamePasswordAuthenticationToken(usuarioEntity,null,authorityList);
        String accessToken= jwtUtil.generateToken(authentication);
        return new AuthResponse(username,"user created",accessToken,true);

    }
    public AuthResponse updateUser(@Valid AuthCreateUserRequest createUserRequest,long id){

        String name= createUserRequest.firstName();
        String lastName= createUserRequest.lastName();
        String email= createUserRequest.institutionalEmail();
        Long phone= createUserRequest.phone();
        Long identifier= createUserRequest.identifier();
        String username= createUserRequest.username();
        String password= createUserRequest.password();
        List<String> roleList= createUserRequest.roleRequest().roleList();

        List<ERoles> roles = roleList.stream()
                .map(ERoles::valueOf)
                .collect(Collectors.toList());

        Set<RoleEntity> roleEntities= roleRepository.findRoleEntitiesByRolesNameIn(roles).stream().collect(Collectors.toSet());
        UserEntity userUpdate= userRepository.findById(id).orElse(null);

        userUpdate.setFirstName(name);
        userUpdate.setLastName(lastName);
        userUpdate.setInstitutionalEmail(email);
        userUpdate.setPhone(phone);
        userUpdate.setIdentifier(identifier);
        userUpdate.setUsername(username);
        userUpdate.setPassword(passwordEncoder.encode(password));
        userUpdate.setRoles(roleEntities);
        userRepository.save(userUpdate);

        UserEntity usuarioEntity= userRepository.save(userUpdate);
        List<SimpleGrantedAuthority> authorityList= new ArrayList<>();

        usuarioEntity.getRoles().stream().forEach(role->
                authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolesName().name()))));

        usuarioEntity.getRoles().stream().flatMap(role->role.getListPermission().stream())
                .forEach(permission->authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        Authentication authentication= new UsernamePasswordAuthenticationToken(usuarioEntity,null,authorityList);
        String accessToken= jwtUtil.generateToken(authentication);
        return new AuthResponse(username,"user update",accessToken,true);

    }


}
