package com.example.usco.tipoTramite;

import jakarta.persistence.*;
import lombok.*;



import com.example.usco.estado.Estado;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tipo_tramite", schema = "public")
public class TipoTramite {

    @Id
    @SequenceGenerator(
        name = "tipoTramite_generator",
        sequenceName = "tipo_tramite_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoTramite_generator")
    @Column(name = "id")
    private Long id;


    @Column(
        name = "nombre"
        , length = 100
        , nullable = false
    )
    private String nombre;



    @ManyToOne(fetch = FetchType.LAZY)
    private Estado estado;



    @PrePersist
    public void prePersist() {


      if (this.estado == null) {
        this.estado = new Estado();
        this.estado.setId(1L);
      }

    }

}
