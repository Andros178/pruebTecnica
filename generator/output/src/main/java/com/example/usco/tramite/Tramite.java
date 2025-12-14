package com.example.usco.tramite;

import jakarta.persistence.*;
import lombok.*;



import com.example.usco.estado.Estado;
import com.example.usco.tipoTramite.TipoTramite;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tramite", schema = "public")
public class Tramite {

    @Id
    @SequenceGenerator(
        name = "tramite_generator",
        sequenceName = "tramite_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tramite_generator")
    @Column(name = "id")
    private Long id;


    @Column(
        name = "descripcion"
        , length = 100
        , nullable = false
    )
    private String descripcion;



    @ManyToOne(fetch = FetchType.LAZY)
    private Estado estado;


    @ManyToOne(fetch = FetchType.LAZY)
    private TipoTramite tipoTramite;



    @PrePersist
    public void prePersist() {


      if (this.estado == null) {
        this.estado = new Estado();
        this.estado.setId(1L);
      }

    }

}
