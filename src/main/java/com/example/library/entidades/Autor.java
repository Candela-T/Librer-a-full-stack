package com.example.library.entidades;

import javax.persistence.*;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private Boolean alta;


    public Autor() {
    }

    public Autor(String nombre, Boolean alta) {

        this.nombre = nombre;
        this.alta = alta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

}
