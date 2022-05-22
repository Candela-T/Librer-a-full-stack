package com.example.library.repositorios;

import com.example.library.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    Autor findByNombre(String nombre);
}
