package hdoo.foro.controller;

import hdoo.foro.domain.topico.DatosRegistroTopico;
import hdoo.foro.domain.topico.RepositoryTopico;
import hdoo.foro.domain.topico.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private RepositoryTopico repositoryTopico;

    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody DatosRegistroTopico datosRegistroTopico){
        var registrar = repositoryTopico.save(new Topico(datosRegistroTopico));

        return ResponseEntity.ok().build();
    }
}
