package com.example.usco.documentoTipoTramite;

import jakarta.persistence.*;
import lombok.*;

import com.example.usco.tipoTramite.TipoTramite;
import com.example.usco.tipoDocumento.TipoDocumento;
import com.example.usco.estado.Estado;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "documento_tipo_tramite", schema = "public")
public class DocumentoTipoTramite {

    @Id
    @SequenceGenerator(
        name = "documentoTipoTramite_generator",
        sequenceName = "documento_tipo_tramite_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documentoTipoTramite_generator")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private TipoTramite tipoTramite;

    @ManyToOne(fetch = FetchType.LAZY)
    private TipoDocumento tipoDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    private Estado estado;

}
