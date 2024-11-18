package com.example.admin.model;

import jakarta.persistence.*;

@Entity
public class Progreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id")
    private Estudiante estudiante;

    private String trimestre;  // Trimestre al que corresponde

    @OneToOne
    @JoinColumn(name = "nota_id", referencedColumnName = "id", nullable = true) // Nota puede ser nula
    private Nota nota;  // Relación con el modelo Nota

    // Constructores
    public Progreso() {}

    public Progreso(Estudiante estudiante, String trimestre, Nota nota) {
        this.estudiante = estudiante;
        this.trimestre = trimestre;
        this.nota = nota;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
}
