package com.biblioteca.controller;

import com.biblioteca.model.Biblioteca;
import com.biblioteca.service.BibliotecaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bibliotecas")
public class BibliotecaController {

    private final BibliotecaService servicio;

    public BibliotecaController(BibliotecaService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<Biblioteca>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Biblioteca> crear(@Valid @RequestBody Biblioteca biblioteca) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.guardar(biblioteca));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> actualizar(@PathVariable Long id, @Valid @RequestBody Biblioteca biblioteca) {
        return ResponseEntity.ok(servicio.actualizar(id, biblioteca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
