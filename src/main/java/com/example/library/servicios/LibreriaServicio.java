package com.example.library.servicios;

import com.example.library.entidades.Autor;
import com.example.library.entidades.Editorial;
import com.example.library.entidades.Libro;
import com.example.library.errores.ErrorServicio;


import java.util.List;

public interface LibreriaServicio {
    void darBajaLibro(Long id);
    void darAltaLibro(Long id);
    Libro busquedaPorIdLibro(Long id);

    void crearLibro(Libro libro ) throws ErrorServicio;

    void editarLibro(Long id, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
                     Integer ejemplaresRestantes, Autor autor, Editorial editorial) throws ErrorServicio;

    void darBajaEditorial(Long id);

    void darAltaEditorial(Long id);

    void crearEditorial(Editorial editorial) throws ErrorServicio;

    void editarNombreEditorial(Long id, String nombre);

    Editorial busquedaEditorialPorId(Long id);

    void crearAutor(Autor autor) throws ErrorServicio;

    void darBajaAutor(Long id);

    void darAltaAutor(Long id);

    Autor busquedaAutorPorId(Long id);

    void editarNombreAutor(Long id, String nombre);

    List<Autor> traerAutores();

    List<Editorial> traerEditoriales();

    List<Libro> traerLibros();

}
