package org.fesc.sicier.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.fesc.sicier.persistence.entities.security.UserEntity;

import java.time.LocalDateTime;

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


    private String status;

    @Column(name = "reception_date")
    private LocalDateTime receptionDate;

    @Column(name = "date_reading")
    private LocalDateTime dateReading;

    @ManyToOne
    @JoinColumn(name = "inform_id")
    private InformEntity inform;

    @ManyToOne
    @JoinColumn(name = "area_destination_id")
    private AreaEntity areaDestination;

    @ManyToOne
    @JoinColumn(name = "user_destination")
    private UserEntity userDestination;


}
