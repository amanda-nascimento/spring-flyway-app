package org.meli.apptestemigration.controller;

import org.meli.apptestemigration.DTO.PessoaDTO;
import org.meli.apptestemigration.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public List<PessoaDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> buscarPorId(@PathVariable Long id) {
        try {
            PessoaDTO dto = service.buscarPorId(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody PessoaDTO dto) {
        service.criar(dto);
        return new ResponseEntity<>("A pessoa foi criada com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody PessoaDTO dto) {
        service.atualizar(id, dto);
        return new ResponseEntity<>("A pessoa foi atualizada com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}