package hdoo.foro.domain.usuario;

import hdoo.foro.domain.respuesta.Respuesta;
import hdoo.foro.domain.topico.Topico;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contraseña;

    @OneToMany(mappedBy = "usuario")
    private List<Topico> topico;

    @OneToMany(mappedBy = "usuario")
    private List<Respuesta> respuesta;
}
