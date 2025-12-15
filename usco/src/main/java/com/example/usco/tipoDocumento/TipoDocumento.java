package com.example.usco.tipoDocumento;

import jakarta.persistence.*;
import lombok.*;

import com.example.usco.estado.Estado;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tipo_documento", schema = "public")
public class TipoDocumento {

    @Id
    @SequenceGenerator(
        name = "tipoDocumento_generator",
        sequenceName = "tipo_documento_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoDocumento_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    private Estado estado;

}
