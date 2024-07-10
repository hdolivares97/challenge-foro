package hdoo.foro.domain.topico;

import hdoo.foro.domain.curso.Curso;
import hdoo.foro.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estatus estatus,
        Usuario usuario,
        Curso curso
) {
}
