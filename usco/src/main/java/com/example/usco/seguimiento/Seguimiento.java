package com.example.usco.seguimiento;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

import com.example.usco.tramite.Tramite;
import com.example.usco.usuario.Usuario;
import com.example.usco.estado.Estado;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "seguimiento", schema = "public")
public class Seguimiento {

    @Id
    @SequenceGenerator(
        name = "seguimiento_generator",
        sequenceName = "seguimiento_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seguimiento_generator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tramite tramite;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Estado estado;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
