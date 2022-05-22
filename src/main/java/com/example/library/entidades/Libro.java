package com.example.library.entidades;


import javax.persistence.*;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private Long isbn;

    @Column(nullable = false)
    private String titulo;

    private Integer anio;

    private Integer ejemplares;

    private Integer ejemplaresPrestados;

    private Integer ejemplaresRestantes;

    private Boolean alta;

    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Editorial editorial;

    public Libro() {
    }

    public Libro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
                 Boolean alta, Autor autor, Editorial editorial, Integer ejemplaresRestantes) {

        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

}
