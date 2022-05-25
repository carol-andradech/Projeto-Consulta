package br.edu.iff.projetoConsulta.controller.apirest;

import br.edu.iff.projetoConsulta.model.Consulta;
import br.edu.iff.projetoConsulta.service.ConsultaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/apirest/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "assistenteId", defaultValue = "0", required = false) Long assistenteId,
            @RequestParam(name = "pacienteId", defaultValue = "0", required = false) Long pacienteId,
            @RequestParam(name = "psicologoId", defaultValue = "0", required = false) Long psicologoId){

        return ResponseEntity.ok(service.findAll(page, size, assistenteId, pacienteId, psicologoId));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Consulta consulta){
        consulta.setId(null);
        service.save(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Consulta consulta){
        consulta.setId(id);
        service.update(consulta);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

