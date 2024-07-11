package hdoo.foro.domain.topico;

import hdoo.foro.domain.curso.Curso;
import hdoo.foro.domain.respuesta.Respuesta;
import hdoo.foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion;
    @Enumerated(EnumType.STRING)
    private Estatus estatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuesta;

    public Topico(String titulo, String mensaje, LocalDateTime fecha_creacion, Usuario usuario, Curso curso) {
        this.estatus = Estatus.ABIERTO;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha_creacion = fecha_creacion;
        this.usuario = usuario;
        this.curso = curso;
    }

    public void actualizarDatos(String titulo, String mensaje, LocalDateTime fecha_creacion, Usuario usuario, Curso curso) {
        if (titulo != null) {
            this.titulo = titulo;
        }
        if (mensaje != null) {
            this.mensaje = mensaje;
        }
        if (fecha_creacion != null) {
            this.fecha_creacion = fecha_creacion;
        }
        if (usuario != null) {
            this.usuario = usuario;
        }
        if (curso != null) {
            this.curso = curso;
        }
    }
}
