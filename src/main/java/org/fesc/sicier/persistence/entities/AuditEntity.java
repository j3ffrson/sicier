package org.fesc.sicier.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "history_changes")
public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "history_changes_seq", sequenceName = "history_changes_seq", allocationSize = 1)
    private Long id;

    @Column(name = "object_name")
    private String ObjectName;

    private String operation;
    private String user;
    private LocalDateTime date;


}
