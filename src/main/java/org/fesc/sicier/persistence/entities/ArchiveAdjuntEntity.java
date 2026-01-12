package org.fesc.sicier.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "archive")
@Builder
@Getter
@Setter
@Entity
public class ArchiveAdjuntEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "archive_seq", sequenceName = "archive_seq", allocationSize = 1)
    private Long id;

    private int informId;
    private String nameFile;
    private String path;
    private String type;

}
