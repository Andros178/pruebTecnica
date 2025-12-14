package com.example.usco.usuarioRol;

import jakarta.persistence.*;
import lombok.*;



import com.example.usco.usuario.Usuario;
import com.example.usco.rol.Rol;
import com.example.usco.estado.Estado;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "usuario_rol", schema = "public")
public class UsuarioRol {

    @Id
    @SequenceGenerator(
        name = "usuarioRol_generator",
        sequenceName = "usuario_rol_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioRol_generator")
    @Column(name = "id")
    private Long id;




    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;


    @ManyToOne(fetch = FetchType.LAZY)
    private Rol rol;


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
