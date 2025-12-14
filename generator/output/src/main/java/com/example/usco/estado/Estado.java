package com.example.usco.estado;

import jakarta.persistence.*;
import lombok.*;




@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "estado", schema = "public")
public class Estado {

    @Id
    @SequenceGenerator(
        name = "estado_generator",
        sequenceName = "estado_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_generator")
    @Column(name = "id")
    private Long id;


    @Column(
        name = "nombre"
        , length = 100
        , nullable = false
    )
    private String nombre;



}
