package org.fesc.sicier.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history_state")
@Builder
@Getter
@Setter
@Entity
public class HistoryStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "state_seq", sequenceName = "steate_seq", allocationSize = 1)
    private Long id;
    @Column(name = "inform_id")
    private int informId;
    @Enumerated(EnumType.STRING)
    private InformStates state;
    @Column(name = "user_id")
    private int userId;
    private LocalDateTime date;
    private String description;


}
