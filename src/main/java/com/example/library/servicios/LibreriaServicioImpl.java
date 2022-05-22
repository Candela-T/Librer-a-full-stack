package com.example.library.servicios;

import com.example.library.entidades.Autor;
import com.example.library.entidades.Editorial;
import com.example.library.entidades.Libro;
import com.example.library.errores.ErrorServicio;
import com.example.library.repositorios.AutorRepositorio;
import com.example.library.repositorios.EditorialRepositorio;
import com.example.library.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class LibreriaServicioImpl implements LibreriaServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void darBajaLibro(Long id) {
        try {
            Optional<Libro> respuesta = libroRepositorio.findById(id);
            if (respuesta.isPresent()) {
                Libro libro = respuesta.get();
                libro.setAlta(false);
                libroRepositorio.save(libro);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void darAltaLibro(Long id) {
        try {
            Optional<Libro> respuesta = libroRepositorio.findById(id);
            if (respuesta.isPresent()) {
                Libro libro = respuesta.get();
                libro.setAlta(true);
                libroRepositorio.save(libro);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


    @Transactional(readOnly = true)
    @Override
    public Libro busquedaPorIdLibro(Long id)  {
        Libro libro = null;
        try {
            Optional<Libro> respuesta = libroRepositorio.findById(id);
            if (respuesta.isPresent()) {
                libro = respuesta.get();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return libro;
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void crearLibro(Libro libro) throws ErrorServicio {
        validarLibro(libro);
        libro.setAlta(true);
        libroRepositorio.save(libro);
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void editarLibro(Long id, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
                            Integer ejemplaresRestantes, Autor autor, Editorial editorial) throws ErrorServicio{


        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if(respuesta.isPresent()){
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            validarLibro(libro);

            libroRepositorio.save(libro);
        }else{

            throw new ErrorServicio("No se encontro el libro solicitado");
        }
    }

    private void validarLibro(Libro libro) throws ErrorServicio {

        if (libro.getTitulo().trim().isEmpty()) {

            throw new ErrorServicio("Debe indicar un titulo para el libro");
        }
        if (libro.getAnio() < 0) {

            throw new ErrorServicio("Debe indicar el anio");
        }
        if (libro.getEjemplares() < 0) {

            throw new ErrorServicio("Debe indicar el total de ejemplares");
        }
        if (libro.getEjemplaresPrestados() < 0) {

            throw new ErrorServicio("Debe indicar el total de ejemplares prestados");
        }

        if(libro.getAutor() == null ){
            throw new ErrorServicio("El autor no puede ser nulo");
        }

        if(libro.getEditorial() == null){
            throw new ErrorServicio("La editorial no puede ser nula");
        }
    }


    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void darBajaEditorial(Long id) {

        try {
            Optional<Editorial> respuesta = editorialRepositorio.findById(id);
            if (respuesta.isPresent()) {
                Editorial editorial = respuesta.get();
                editorial.setAlta(false);
                editorialRepositorio.save(editorial);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void darAltaEditorial(Long id) {
        try {
            Optional<Editorial> respuesta = editorialRepositorio.findById(id);
            if (respuesta.isPresent()) {
                Editorial editorial = respuesta.get();
                editorial.setAlta(true);
                editorialRepositorio.save(editorial);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void crearEditorial(Editorial editorial) throws ErrorServicio{
        validarEditorial(editorial);
        editorial.setAlta(true);
        editorialRepositorio.save(editorial);
    }


    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void editarNombreEditorial(Long id, String nombre) {
        try{
            Optional<Editorial> respuesta = editorialRepositorio.findById(id);
            if (respuesta.isPresent()) {
                Editorial editorial = respuesta.get();
                editorial.setNombre(nombre);
                editorialRepositorio.save(editorial);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Transactional(readOnly = true)
    @Override
    public Editorial busquedaEditorialPorId(Long id) {
        Editorial editorial = null;
        try {
            Optional<Editorial> respuesta = editorialRepositorio.findById(id);
            if (respuesta.isPresent()) {
                editorial = respuesta.get();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return editorial;
    }



    private void validarEditorial(Editorial editorial) throws ErrorServicio {

        if(editorial.getNombre().trim().isEmpty()){
            throw new ErrorServicio("El nombre no puede estar vacio");
        }


    }


    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void crearAutor(Autor autor) throws ErrorServicio {
        validarAutor(autor);
        autor.setAlta(true);
        autorRepositorio.save(autor);
    }


    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void darBajaAutor(Long id) {
        try {
            Optional<Autor> respuesta = autorRepositorio.findById(id);
            if (respuesta.isPresent()) {
                Autor autor = respuesta.get();
                autor.setAlta(false);
                autorRepositorio.save(autor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void darAltaAutor(Long id) {
        try {
            Optional<Autor> respuesta = autorRepositorio.findById(id);
            if (respuesta.isPresent()) {
                Autor autor = respuesta.get();
                autor.setAlta(true);
                autorRepositorio.save(autor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Autor busquedaAutorPorId(Long id) {
        Autor autor = null;
        try {
            Optional<Autor> respuesta = autorRepositorio.findById(id);
            if (respuesta.isPresent()) {
                autor = respuesta.get();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return autor;
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void editarNombreAutor(Long id, String nombre) {
        try {
            Optional<Autor>  respuesta = autorRepositorio.findById(id);
            if (respuesta.isPresent()){
                Autor autor = respuesta.get();
                autor.setNombre(nombre);
                autorRepositorio.save(autor);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Autor> traerAutores() {
        return autorRepositorio.findAll();
    }

    @Override
    public List<Editorial> traerEditoriales() {
        return editorialRepositorio.findAll();
    }

    @Override
    public List<Libro> traerLibros() {
        return libroRepositorio.findAll();
    }

    private void validarAutor(Autor autor)  throws ErrorServicio{

        if(autor.getNombre().trim().isEmpty()){
            throw new ErrorServicio("El nombre no puede estar vacio");
        }
    }
}









