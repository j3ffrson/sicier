package org.fesc.sicier.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.fesc.sicier.services.StateDestination;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StateDestination state;

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
