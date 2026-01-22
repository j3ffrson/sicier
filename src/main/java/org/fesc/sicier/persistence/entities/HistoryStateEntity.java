package org.fesc.sicier.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history_states")
@Builder
@Getter
@Setter
@Entity
public class HistoryStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inform_id")
    private Long informId;

    @Column(name = "request_id")
    private Long requestId;

    @Enumerated(EnumType.STRING)
    private InformStates state;

    @Enumerated(EnumType.STRING)
    private RequestStates requestState;

    @Column(name = "user_id")
    private Long userId;

    private LocalDateTime date;
    private String description;


}
