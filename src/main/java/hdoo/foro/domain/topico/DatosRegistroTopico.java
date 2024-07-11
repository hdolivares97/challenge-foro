package hdoo.foro.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        @Future
        LocalDateTime fecha_creacion,
        @NotNull
        Long usuario,
        @NotNull
        Long curso
) {
}
