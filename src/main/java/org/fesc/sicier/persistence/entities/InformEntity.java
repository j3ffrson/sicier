package org.fesc.sicier.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.persistence.listener.AuditEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inform")
@Builder
@Getter
@Setter
@Entity
public class InformEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
