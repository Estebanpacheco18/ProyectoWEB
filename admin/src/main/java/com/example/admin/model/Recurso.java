package com.example.admin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recursos")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String url;

    @ManyToOne
    @JoinColumn(name = "seccion_id")  // Nombre de la columna que será la clave foránea en la tabla "recursos"
    private Seccion seccion;  // Relación con la entidad Seccion

    public Recurso() {}

    public Recurso(String nombre, String url, Seccion seccion) {
        this.nombre = nombre;
        this.url = url;
        this.seccion = seccion;
    }

    // Getters y Setters
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
}
