package com.example.admin.model;

import jakarta.persistence.*;
import java.time.LocalDate; // Asegúrate de importar la clase LocalDate

@Entity
@Table(name = "actividades")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    // Cambiar 'duracion' por 'fecha' de tipo LocalDate
    private LocalDate fecha; // Nuevo campo de tipo LocalDate

    // Relación con Seccion
    @ManyToOne
    @JoinColumn(name = "seccion_id", nullable = false) // La columna seccion_id será la clave foránea
    private Seccion seccion;  // Relación con la entidad Seccion

    public Actividad() {}

    public Actividad(String nombre, String descripcion, LocalDate fecha, Seccion seccion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
}
