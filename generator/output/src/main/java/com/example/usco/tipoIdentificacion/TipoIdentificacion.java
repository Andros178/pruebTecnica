package com.example.usco.tipoIdentificacion;

import jakarta.persistence.*;
import lombok.*;



import com.example.usco.estado.Estado;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tipo_identificacion", schema = "public")
public class TipoIdentificacion {

    @Id
    @SequenceGenerator(
        name = "tipoIdentificacion_generator",
        sequenceName = "tipo_identificacion_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoIdentificacion_generator")
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
