package com.biblioteca.service;

import com.biblioteca.model.Biblioteca;
import com.biblioteca.model.Libro;
import com.biblioteca.repository.BibliotecaRepository;
import com.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LibroService {

    private final LibroRepository libroRepositorio;
    private final BibliotecaRepository bibliotecaRepositorio;

    public LibroService(LibroRepository libroRepositorio, BibliotecaRepository bibliotecaRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.bibliotecaRepositorio = bibliotecaRepositorio;
    }

    public List<Libro> listar() {
        return libroRepositorio.findAll();
    }

    public Libro buscarPorId(Long id) {
        return libroRepositorio.findById(id).orElseThrow();
    }

    public Libro guardar(Libro libro) {
        Biblioteca biblioteca = bibliotecaRepositorio.findById(libro.getBiblioteca().getId()).orElseThrow();
        libro.setBiblioteca(biblioteca);
        libro.setId(null);
        return libroRepositorio.save(libro);
    }

    public Libro actualizar(Long id, Libro datos) {
        Libro libro = libroRepositorio.findById(id).orElseThrow();
        libro.setTitulo(datos.getTitulo());
        libro.setAutor(datos.getAutor());
        libro.setCategoria(datos.getCategoria());
        if (datos.getBiblioteca() != null) {
            Biblioteca biblioteca = bibliotecaRepositorio.findById(datos.getBiblioteca().getId()).orElseThrow();
            libro.setBiblioteca(biblioteca);
        }
        return libroRepositorio.save(libro);
    }

    public void eliminar(Long id) {
        libroRepositorio.deleteById(id);
    }
}
