package hdoo.foro.controller;

import hdoo.foro.domain.curso.RepositoryCurso;
import hdoo.foro.domain.topico.DatosRegistroTopico;
import hdoo.foro.domain.topico.RepositoryTopico;
import hdoo.foro.domain.topico.Topico;
import hdoo.foro.domain.usuario.RepositoryUsuario;
import hdoo.foro.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private RepositoryTopico repositoryTopico;

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    @Autowired
    private RepositoryCurso repositoryCurso;

    @PostMapping
    public void registrarTopico(@RequestBody DatosRegistroTopico datosRegistroTopico){
        //System.out.println(datosRegistroTopico);
        var usuario = repositoryUsuario.findById(datosRegistroTopico.usuario()).get();
        var curso = repositoryCurso.findById(datosRegistroTopico.curso()).get();
        //System.out.println(curso);
        var data = repositoryTopico.save(new Topico(
                datosRegistroTopico.titulo(),
                datosRegistroTopico.mensaje(),
                datosRegistroTopico.fecha_creacion(),
                usuario,
                curso));
    }
}
