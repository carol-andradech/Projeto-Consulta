package br.edu.iff.projetoConsulta.controller.apirest;

import br.edu.iff.projetoConsulta.model.Assistente;
import br.edu.iff.projetoConsulta.service.AssistenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/apirest/assistentes")
public class AssistenteController {
    @Autowired
    private AssistenteService assistenteService;

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {

        return ResponseEntity.ok(assistenteService.findAll(page, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(assistenteService.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Assistente assistente) {
        assistente.setId(null);
        assistenteService.save(assistente);
        return ResponseEntity.status(HttpStatus.CREATED).body(assistente);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Assistente assistente) {
        assistente.setId(id);
        assistenteService.update(assistente);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        assistenteService.delete(id);
        return ResponseEntity.ok().build();
    }
}


