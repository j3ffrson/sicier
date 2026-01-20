package org.fesc.sicier.persistence.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.fesc.sicier.persistence.entities.security.AreaEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;

import java.time.LocalDateTime;
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
    private LocalDateTime creationDate;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "inform" )
    private List<DestinationInformEntity> destinations= new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEmisor;

    @ManyToOne()
    @JoinColumn(name = "area_id")
    private AreaEntity areaEmisor;


}
