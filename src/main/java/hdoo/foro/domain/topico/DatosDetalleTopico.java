package hdoo.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        Long usuario,
        Long curso
){

    public DatosDetalleTopico(Topico topico){
        this(
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getFecha_creacion(),
            topico.getUsuario().getId(),
            topico.getCurso().getId()
        );
    }
}
