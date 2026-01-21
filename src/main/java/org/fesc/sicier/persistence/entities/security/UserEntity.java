package org.fesc.sicier.persistence.entities.security;

import jakarta.persistence.*;
import lombok.*;
import org.fesc.sicier.persistence.entities.AreaEntity;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
@Builder
@Getter
@Setter
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private Long phone;
    @Column(nullable = false,unique = true)
    private Long identifier;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false,name = "institutional_email")
    private String institutionalEmail;
    @Column(nullable = false)
    private String password;

    @Column(name = "enabled")
    private boolean isEnabled;

    @Column(name = "account_non_expired")
    private boolean isAccountNonExpired;

    @Column(name = "account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean isCredentialsNonExpired;

    @ManyToOne(optional = false)
    @JoinColumn(name = "area_id")
    private AreaEntity area;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

}
