package com.example.usco.comentario;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

import com.example.usco.usuario.Usuario;
import com.example.usco.tramite.Tramite;
import com.example.usco.estado.Estado;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "comentario", schema = "public")
public class Comentario {

    @Id
    @SequenceGenerator(
        name = "comentario_generator",
        sequenceName = "comentario_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comentario_generator")
    private Long id;

    @Column(name = "mensaje", length = 1000, nullable = false)
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tramite tramite;

    @ManyToOne(fetch = FetchType.LAZY)
    private Estado estado;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }

        if (this.estado == null) {
        this.estado = new Estado();
        this.estado.setId(1L);
      }
    }
}
