package com.example.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.admin.model.Actividad;
import com.example.admin.repository.ActividadRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    // Obtener todas las actividades
    public List<Actividad> getAllActividades() {
        return actividadRepository.findAll();
    }

    // Obtener una actividad por su ID
    public Optional<Actividad> getActividadById(Long id) {
        return actividadRepository.findById(id);
    }

    // Crear una nueva actividad
    public Actividad createActividad(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    // Actualizar una actividad
    public Actividad updateActividad(Long id, Actividad actividad) {
        if (actividadRepository.existsById(id)) {
            actividad.setId(id);  // Asegura que el ID sea correcto
            return actividadRepository.save(actividad);
        }
        return null;
    }

    // Eliminar una actividad
    public void deleteActividad(Long id) {
        actividadRepository.deleteById(id);
    }

    // Buscar actividades por nombre
    public List<Actividad> findActividadesByNombre(String nombre) {
        return actividadRepository.findByNombreContaining(nombre);
    }
}
