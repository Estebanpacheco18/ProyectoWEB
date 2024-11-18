package com.example.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.admin.model.Estudiante;
import com.example.admin.repository.EstudianteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Obtener todos los estudiantes
    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    // Obtener un estudiante por su ID
    public Optional<Estudiante> getEstudianteById(Long id) {
        return estudianteRepository.findById(id);
    }

    // Obtener un estudiante por su nombre
    public Optional<Estudiante> getEstudianteByNombre(String nombre) {
        return Optional.ofNullable(estudianteRepository.findByNombre(nombre));
    }

    // Obtener estudiantes por su sección
    public List<Estudiante> getEstudiantesBySeccion(String seccion) {
        return estudianteRepository.findBySeccion(seccion);
    }

    // Crear o actualizar un estudiante
    public Estudiante saveEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);  // Save maneja ambos casos
    }
    // Eliminar un estudiante
    public void deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }
}
