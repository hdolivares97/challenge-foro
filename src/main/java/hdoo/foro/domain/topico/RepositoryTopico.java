package hdoo.foro.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryTopico extends JpaRepository<Topico, Long> {

    boolean findByTituloOrMensaje(String titulo, String mensaje);
}
