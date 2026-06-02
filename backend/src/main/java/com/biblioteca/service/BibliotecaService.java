package com.biblioteca.service;

import com.biblioteca.model.Biblioteca;
import com.biblioteca.repository.BibliotecaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BibliotecaService {

    private final BibliotecaRepository repositorio;

    public BibliotecaService(BibliotecaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Biblioteca> listar() {
        return repositorio.findAll();
    }

    public Biblioteca buscarPorId(Long id) {
        return repositorio.findById(id).orElseThrow();
    }

    public Biblioteca guardar(Biblioteca biblioteca) {
        biblioteca.setId(null);
        return repositorio.save(biblioteca);
    }

    public Biblioteca actualizar(Long id, Biblioteca datos) {
        Biblioteca biblioteca = repositorio.findById(id).orElseThrow();
        biblioteca.setNombre(datos.getNombre());
        biblioteca.setDireccion(datos.getDireccion());
        biblioteca.setTelefono(datos.getTelefono());
        biblioteca.setResponsable(datos.getResponsable());
        return repositorio.save(biblioteca);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}
