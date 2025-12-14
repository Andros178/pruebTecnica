package com.example.usco.persona;

import jakarta.persistence.*;
import lombok.*;



import com.example.usco.tipoIdentificacion.TipoIdentificacion;
import com.example.usco.estado.Estado;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "persona", schema = "public")
public class Persona {

    @Id
    @SequenceGenerator(
        name = "persona_generator",
        sequenceName = "persona_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_generator")
    @Column(name = "id")
    private Long id;


    @Column(
        name = "nombre"
        , length = 100
        , nullable = false
    )
    private String nombre;

    @Column(
        name = "segundo_nombre"
        , length = 100
        , nullable = true
    )
    private String segundoNombre;

    @Column(
        name = "apellido"
        , length = 100
        , nullable = false
    )
    private String apellido;

    @Column(
        name = "segundo_apellido"
        , length = 100
        , nullable = true
    )
    private String segundoApellido;

    @Column(
        name = "correo"
        , length = 100
        , nullable = false
    )
    private String correo;

    @Column(
        name = "numero_identificacion"
        , length = 100
        , nullable = false
    )
    private String numeroIdentificacion;



    @ManyToOne(fetch = FetchType.LAZY)
    private TipoIdentificacion tipoIdentificacion;


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
