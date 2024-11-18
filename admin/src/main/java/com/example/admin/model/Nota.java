package com.example.admin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Las propiedades son de tipo Double, que ya permite valores nulos.
    private Double nota1;
    private Double nota2;
    private Double nota3;
    private Double nota4;
    private Double nota5;
    private Double nota6;
    private Double nota7;
    private Double nota8;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }

    public Double getNota3() {
        return nota3;
    }

    public void setNota3(Double nota3) {
        this.nota3 = nota3;
    }

    public Double getNota4() {
        return nota4;
    }

    public void setNota4(Double nota4) {
        this.nota4 = nota4;
    }

    public Double getNota5() {
        return nota5;
    }

    public void setNota5(Double nota5) {
        this.nota5 = nota5;
    }

    public Double getNota6() {
        return nota6;
    }

    public void setNota6(Double nota6) {
        this.nota6 = nota6;
    }

    public Double getNota7() {
        return nota7;
    }

    public void setNota7(Double nota7) {
        this.nota7 = nota7;
    }

    public Double getNota8() {
        return nota8;
    }

    public void setNota8(Double nota8) {
        this.nota8 = nota8;
    }
}
