package com.example.library.repositorios;

import com.example.library.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    Libro findByTitulo(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombreAutor")
    List<Libro> buscarLibrosPorAutor(@Param("nombreAutor") String nombre);

    List<Libro> findByEditorial(String nombre);




}
