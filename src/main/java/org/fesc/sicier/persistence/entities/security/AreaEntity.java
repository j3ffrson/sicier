package org.fesc.sicier.persistence.entities.security;

import jakarta.persistence.*;
import lombok.*;
import org.fesc.sicier.persistence.entities.InformEntity;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "area")
@Builder
@Getter
@Setter
@Entity
public class AreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "area_seq", sequenceName = "area_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Boolean active;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<UserEntity> users= new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<InformEntity> informs= new ArrayList<>();

    public void setUser(UserEntity user){
        this.users.add(user);
        user.setAreaEntities(this);
    }

}
