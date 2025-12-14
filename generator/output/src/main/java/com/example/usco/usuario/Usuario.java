package com.example.usco.usuario;

import jakarta.persistence.*;
import lombok.*;



import com.example.usco.persona.Persona;
import com.example.usco.estado.Estado;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "usuario", schema = "public")
public class Usuario {

    @Id
    @SequenceGenerator(
        name = "usuario_generator",
        sequenceName = "usuario_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_generator")
    @Column(name = "id")
    private Long id;


    @Column(
        name = "nombre"
        , length = 100
        , nullable = false
    )
    private String nombre;

    @Column(
        name = "contrasena"
        , length = 100
        , nullable = false
    )
    private String contrasena;



    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;


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
