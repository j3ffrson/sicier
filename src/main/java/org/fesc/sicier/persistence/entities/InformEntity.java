package org.fesc.sicier.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.fesc.sicier.persistence.entities.security.AreaEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inform")
@Builder
@Getter
@Setter
@Entity
public class InformEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "inform_seq", sequenceName = "inform_seq", allocationSize = 1)
    private Long id;

    private String title;
    private String description;
    private String status;
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "emisor_id")
    private UserEntity UserEmisor;

    @OneToMany
    private List<DestinationInformEntity> destinationInformEntity= new ArrayList<>();

    @ManyToOne(optional = false)
    private AreaEntity areaEntities;

}
