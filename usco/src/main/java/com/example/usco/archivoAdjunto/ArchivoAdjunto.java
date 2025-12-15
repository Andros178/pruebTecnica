package com.example.usco.archivoAdjunto;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

import com.example.usco.tramite.Tramite;
import com.example.usco.estado.Estado;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "archivo_adjunto", schema = "public")
public class ArchivoAdjunto {

    @Id
    @SequenceGenerator(
        name = "archivoAdjunto_generator",
        sequenceName = "archivo_adjunto_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "archivoAdjunto_generator")
    @Column(name = "id")
    private Long id;


    @Column(
        name = "mime"
        , length = 255
        , nullable = false
    )
    private String mime;

    @Column(
        name = "nombre_archivo"
        , length = 255
        , nullable = false
    )
    private String nombreArchivo;

    @Column(
        name = "fecha_creacion"
        , length = 255
        , nullable = false
    )
    private java.time.LocalDateTime fechaCreacion;

    @Column(
        name = "tamano"
        , length = 255
        , nullable = false
    )
    private String tamano;



    @ManyToOne(fetch = FetchType.LAZY)
    private Tramite tramite;

    @ManyToOne(fetch = FetchType.LAZY)
    private Estado estado;



    @PrePersist
    public void prePersist() {

      if (this.fechaCreacion == null) {
        this.fechaCreacion = LocalDateTime.now();
      }

      if (this.estado == null) {
        this.estado = new Estado();
        this.estado.setId(1L);
      }

    }

}
