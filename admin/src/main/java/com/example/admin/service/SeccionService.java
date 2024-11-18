package com.example.admin.service;

import com.example.admin.model.Seccion;
import com.example.admin.repository.SeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeccionService {

    @Autowired
    private SeccionRepository seccionRepository;

    // Obtener todas las secciones
    public List<Seccion> getAllSecciones() {
        return seccionRepository.findAll();
    }

    // Obtener una sección por su ID
    public Optional<Seccion> getSeccionById(Long id) {
        return seccionRepository.findById(id);
    }

    // Crear una nueva sección
    public Seccion createSeccion(Seccion seccion) {
        return seccionRepository.save(seccion);
    }

    // Actualizar una sección
    public Seccion updateSeccion(Long id, Seccion seccion) {
        if (seccionRepository.existsById(id)) {
            seccion.setId(id);
            return seccionRepository.save(seccion);
        }
        return null;
    }

    // Eliminar una sección
    public void deleteSeccion(Long id) {
        seccionRepository.deleteById(id);
    }
}
