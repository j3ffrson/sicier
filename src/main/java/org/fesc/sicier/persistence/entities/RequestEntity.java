package org.fesc.sicier.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.fesc.sicier.persistence.entities.security.AreaEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "request")
@Builder
@Getter
@Setter
@Entity
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "req_seq", sequenceName = "req_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 2000)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStates estado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "solicitante_id")
    private UserEntity solicitante;

    @ManyToOne
    @JoinColumn(name = "area_destino_id")
    private AreaEntity areaDestino;

    @ManyToOne
    @JoinColumn(name = "usuario_destino_id")
    private UserEntity usuarioDestino;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaRespuesta;

    @Column(length = 2000)
    private String respuesta;

}
