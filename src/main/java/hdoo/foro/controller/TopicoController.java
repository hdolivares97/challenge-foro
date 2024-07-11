package hdoo.foro.controller;

import hdoo.foro.domain.curso.RepositoryCurso;
import hdoo.foro.domain.topico.*;
import hdoo.foro.domain.usuario.RepositoryUsuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@ResponseBody
public class TopicoController {

    @Autowired
    private RepositoryTopico repositoryTopico;

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    @Autowired
    private RepositoryCurso repositoryCurso;

    @Autowired
    private DatosTopicoService datosTopicoService;

    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
        var response = datosTopicoService.registrar(datosRegistroTopico);
        //System.out.println(datosRegistroTopico);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listarMedicos(Pageable paginacion){
        return ResponseEntity.ok(repositoryTopico.findAll(paginacion).map(DatosDetalleTopico::new));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> retornaDatosMedico(@PathVariable Long id){
        Topico topico = repositoryTopico.getReferenceById(id);
        var datosTopicos = new DatosRespuestaTopico(topico.getId(),
                topico.getTitulo(), topico.getMensaje(), topico.getFecha_creacion(),
                topico.getUsuario().getId(), topico.getCurso().getId());
        return ResponseEntity.ok(datosTopicos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        var response = datosTopicoService.actualizarTopico(datosActualizarTopico);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Topico topico = repositoryTopico.getReferenceById(id);
        repositoryTopico.delete(topico);
        return ResponseEntity.noContent().build();
    }
}
