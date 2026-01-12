package org.fesc.sicier.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.fesc.sicier.persistence.entities.security.AreaEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "destination_inform")
@Builder
@Getter
@Setter
@Entity
public class DestinationInformEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "dest_seq", sequenceName = "dest_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private InformEntity inform_id;

    private String status;

    @Column(name = "reception_date")
    private LocalDateTime receptionDate;

    @Column(name = "date_reading")
    private LocalDateTime date_reading;

    @ManyToOne
    @JoinColumn(name = "area_destination_id")
    private AreaEntity areaDestino;



}
