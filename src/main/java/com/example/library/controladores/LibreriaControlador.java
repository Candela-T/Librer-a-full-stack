package com.example.library.controladores;

import com.example.library.dto.LibroFormulario;
import com.example.library.entidades.Autor;
import com.example.library.entidades.Editorial;
import com.example.library.entidades.Libro;
import com.example.library.errores.ErrorServicio;
import com.example.library.servicios.LibreriaServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class LibreriaControlador {

    @Autowired
    private LibreriaServicioImpl libroServicio;

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping("/libros")
    public String cargarLibrosHTML(Model model){

        LibroFormulario libroFormulario = new LibroFormulario();
        libroFormulario.setAutores(libroServicio.traerAutores());
        libroFormulario.setEditoriales(libroServicio.traerEditoriales());
        model.addAttribute("libroFormulario", libroFormulario);
        model.addAttribute("libros", libroServicio.traerLibros());

        return "libros";
    }

    @PostMapping("/libros")
    public String guardarLibro(@ModelAttribute LibroFormulario libroFormulario, Model model){

        Libro libro = new Libro();

        libro.setAlta(libroFormulario.getAlta());
        libro.setAnio(libroFormulario.getAnio());
        libro.setEjemplares(libroFormulario.getEjemplares());
        libro.setEjemplaresPrestados(libroFormulario.getEjemplaresPrestados());
        libro.setEjemplaresRestantes(libroFormulario.getEjemplares() - libroFormulario.getEjemplaresPrestados());
        libro.setTitulo(libroFormulario.getTitulo());
        libro.setIsbn(libroFormulario.getIsbn());
        libro.setAutor(libroFormulario.getAutores().get(0));
        libro.setEditorial(libroFormulario.getEditoriales().get(0));

        try {
            libroServicio.crearLibro(libro);
        } catch (ErrorServicio e) {
            e.printStackTrace();
            return "libros";
        }

        LibroFormulario nuevoLibroFormulario = new LibroFormulario();
        nuevoLibroFormulario.setAutores(libroServicio.traerAutores());
        nuevoLibroFormulario.setEditoriales(libroServicio.traerEditoriales());

        model.addAttribute("libroFormulario", nuevoLibroFormulario);
        model.addAttribute("libros", libroServicio.traerLibros());

        return "libros";
    }

    @GetMapping("/editoriales")
    public String cargarEditorialesHTML(Model model){
        model.addAttribute("editorial", new Editorial());
        model.addAttribute("editoriales", libroServicio.traerEditoriales());
        return "editoriales";
    }

    @PostMapping("/editoriales")
    public String guardarEditorial(@ModelAttribute Editorial editorial, Model model){

        try {
            libroServicio.crearEditorial(editorial);
        } catch (ErrorServicio e) {
            e.printStackTrace();
            return "editoriales";
        }

        model.addAttribute("editorial", new Editorial());
        model.addAttribute("editoriales", libroServicio.traerEditoriales());
        return "editoriales";
    }

    @GetMapping("/altaEditorial")
    public String darAltaEditorial(@RequestParam Long editorialId, Model model) {

        Editorial editorial = libroServicio.busquedaEditorialPorId(editorialId);

        if (editorial.getAlta())
            libroServicio.darBajaEditorial(editorial.getId());
        else
            libroServicio.darAltaEditorial(editorial.getId());

        model.addAttribute("editorial", new Editorial());
        model.addAttribute("editoriales", libroServicio.traerEditoriales());
        return "editoriales";
    }

    @GetMapping("/altaLibro")
    public String darAltaLibro(@RequestParam Long libroId, Model model) {

        Libro libro = libroServicio.busquedaPorIdLibro(libroId);

        if (libro.getAlta())
            libroServicio.darBajaLibro(libro.getId());
        else
            libroServicio.darAltaLibro(libro.getId());

        LibroFormulario libroFormulario = new LibroFormulario();
        libroFormulario.setAutores(libroServicio.traerAutores());
        libroFormulario.setEditoriales(libroServicio.traerEditoriales());

        model.addAttribute("libroFormulario", libroFormulario);
        model.addAttribute("libros", libroServicio.traerLibros());
        return "libros";
    }

    @GetMapping("/autores")
    public String cargarAutoresHTML(Model model){
        model.addAttribute("autor", new Autor());
        model.addAttribute("autores", libroServicio.traerAutores());
        return "autores";
    }

    @GetMapping("/altaAutor")
    public String darAltaAutor(@RequestParam Long autorId, Model model) {

        Autor autor = libroServicio.busquedaAutorPorId(autorId);

        if (autor.getAlta())
            libroServicio.darBajaAutor(autor.getId());
        else
            libroServicio.darAltaAutor(autor.getId());

        model.addAttribute("autor", new Autor());
        model.addAttribute("autores", libroServicio.traerAutores());
        return "autores";
    }

    @PostMapping("/autores")
    public String guardarAutor( @ModelAttribute Autor autor, Model model){

        try {
            libroServicio.crearAutor(autor);
        } catch (ErrorServicio e) {
            e.printStackTrace();
            return "autores";
        }
        model.addAttribute("autor", new Autor());
        model.addAttribute("autores", libroServicio.traerAutores());
        return "autores";
    }

    @GetMapping("/editarAutor")
    public String cargarEditarAutorHTML(@RequestParam Long autorId, Model model){

        model.addAttribute("autor", libroServicio.busquedaAutorPorId(autorId));
        return "editarAutor";
    }

    @PostMapping("/editarAutor")
    public String editarAutor(@ModelAttribute Autor autor, Model model){

        libroServicio.editarNombreAutor(autor.getId(), autor.getNombre());

        model.addAttribute("autor", new Autor());
        model.addAttribute("autores", libroServicio.traerAutores());

        return "autores";
    }

    @GetMapping("/editarEditorial")
    public String cargarEditarEditorialHTML(@RequestParam Long editorialId, Model model){

        model.addAttribute("editorial", libroServicio.busquedaEditorialPorId(editorialId));
        return "editarEditorial";
    }

    @PostMapping("/editarEditorial")
    public String editarEditorial(@ModelAttribute Editorial editorial, Model model){

        libroServicio.editarNombreEditorial(editorial.getId(), editorial.getNombre());

        model.addAttribute("editorial", new Editorial());
        model.addAttribute("editoriales", libroServicio.traerEditoriales());

        return "editoriales";
    }

    @GetMapping("/editarLibro")
    public String cargarEditarLibroHTML(@RequestParam Long libroId, Model model){


        Libro libro = libroServicio.busquedaPorIdLibro(libroId);
        LibroFormulario libroFormulario = new LibroFormulario();
        libroFormulario.setId(libroId);
        libroFormulario.setTitulo(libro.getTitulo());
        libroFormulario.setIsbn(libro.getIsbn());
        libroFormulario.setAnio(libro.getAnio());
        libroFormulario.setEjemplares(libro.getEjemplares());
        libroFormulario.setEjemplaresPrestados(libro.getEjemplaresPrestados());
        libroFormulario.setAutores(libroServicio.traerAutores());
        libroFormulario.setEditoriales(libroServicio.traerEditoriales());
        libroFormulario.setAutor(libro.getAutor());
        libroFormulario.setEditorial(libro.getEditorial());


        model.addAttribute("libroFormulario", libroFormulario);
        model.addAttribute("libros", libroServicio.traerLibros());
        return "editarLibro";
    }


    @PostMapping("/editarLibro")
    public String editarLibro(@ModelAttribute LibroFormulario libroFormulario, Model model) throws ErrorServicio {

        libroFormulario.setEjemplaresRestantes(
                libroFormulario.getEjemplares() - libroFormulario.getEjemplaresPrestados());

        libroServicio.editarLibro(libroFormulario.getId(),
                libroFormulario.getTitulo(),
                libroFormulario.getAnio(),
                libroFormulario.getEjemplares(),
                libroFormulario.getEjemplaresPrestados(),
                libroFormulario.getEjemplaresRestantes(),
                libroFormulario.getAutores().get(0),
                libroFormulario.getEditoriales().get(0));

        LibroFormulario nuevoLibroFormulario = new LibroFormulario();
        nuevoLibroFormulario.setAutores(libroServicio.traerAutores());
        nuevoLibroFormulario.setEditoriales(libroServicio.traerEditoriales());

        model.addAttribute("libroFormulario", nuevoLibroFormulario);
        model.addAttribute("libros", libroServicio.traerLibros());

        return "libros";
    }
}





