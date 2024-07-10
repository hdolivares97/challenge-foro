package hdoo.foro.domain.curso;

import hdoo.foro.domain.topico.Topico;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Curso")
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Categoria categoria;

    @OneToMany(mappedBy = "curso")
    private List<Topico> topico;
}
