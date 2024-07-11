package hdoo.foro.domain.topico;

import hdoo.foro.domain.curso.Curso;
import hdoo.foro.domain.curso.RepositoryCurso;
import hdoo.foro.domain.usuario.RepositoryUsuario;
import hdoo.foro.domain.usuario.Usuario;
import hdoo.foro.infra.ValidacionDeIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DatosTopicoService {

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    @Autowired
    private RepositoryCurso repositoryCurso;

    @Autowired
    private RepositoryTopico repositoryTopico;

    public DatosDetalleTopico registrar(DatosRegistroTopico datosRegistroTopico){
        if (!repositoryCurso.findById(datosRegistroTopico.curso()).isPresent()) {
            throw new ValidacionDeIntegridad("No existe el curso");
        }
        var topicoDuplicado = repositoryTopico.findByTituloOrMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje());

        if (topicoDuplicado) {
            throw new ValidacionDeIntegridad("Ya existe un topico");
        }

        var usuario = repositoryUsuario.findById(datosRegistroTopico.usuario()).get();
        var curso = repositoryCurso.findById(datosRegistroTopico.curso()).get();

        var consulta = new Topico(
                datosRegistroTopico.titulo(),
                datosRegistroTopico.mensaje(),
                datosRegistroTopico.fecha_creacion(),
                usuario,
                curso);

        repositoryTopico.save(consulta);
        return new DatosDetalleTopico(consulta);
    }

    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
        Topico topico = repositoryTopico.findById(datosActualizarTopico.id())
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado."));

        Usuario usuario = null;
        if (datosActualizarTopico.usuario() != null) {
            usuario = repositoryUsuario.findById(datosActualizarTopico.usuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        }

        Curso curso = null;
        if (datosActualizarTopico.curso() != null) {
            curso = repositoryCurso.findById(datosActualizarTopico.curso())
                    .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado."));
        }

        topico.actualizarDatos(
                datosActualizarTopico.titulo(),
                datosActualizarTopico.mensaje(),
                datosActualizarTopico.fecha_creacion(),
                usuario,
                curso
        );

        repositoryTopico.save(topico);
        return ResponseEntity.ok(new DatosRespuestaTopico(
                datosActualizarTopico.id(),
                datosActualizarTopico.titulo(),
                datosActualizarTopico.mensaje(),
                datosActualizarTopico.fecha_creacion(),
                datosActualizarTopico.usuario(),
                datosActualizarTopico.curso()));
    }
}
